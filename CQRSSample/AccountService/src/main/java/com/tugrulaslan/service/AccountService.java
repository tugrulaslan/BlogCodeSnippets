package com.tugrulaslan.service;

import com.tugrulaslan.dto.*;
import com.tugrulaslan.entity.AccountEntity;
import com.tugrulaslan.exception.AccountHasInsufficientFundsException;
import com.tugrulaslan.exception.AccountNotFoundException;
import com.tugrulaslan.repository.AccountRepository;
import com.tugrulaslan.stream.AccountStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeTypeUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@EnableBinding(AccountStream.class)
public class AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository accountRepository;
    private final AccountStream accountStream;

    public AccountService(AccountRepository accountRepository, AccountStream accountStream) {
        this.accountRepository = accountRepository;
        this.accountStream = accountStream;
    }

    public AccountSummaryDto retrieveById(Long id) {
        AccountEntity accountEntity = retrieveAccount(id);
        LOGGER.info("retrieved account by id '%s'", id);
        return mapToAccountDto(accountEntity);
    }

    public AccountSummaryDto create(@Valid @NotNull AccountCreationRequestDto accountDto) {
        AccountEntity accountEntity = AccountEntity.builder()
                .balance(accountDto.getBalance())
                .build();
        accountEntity = accountRepository.save(accountEntity);
        LOGGER.info("persisted account {}", accountEntity);
        return mapToAccountDto(accountEntity);
    }

    @Transactional
    public AccountSummaryDto update(TransactionRequestDto transactionRequestDto) {
        AccountEntity account = retrieveAccount(transactionRequestDto.getAccountId());
        account = updateAccountBalance(transactionRequestDto.getAccountId(), transactionRequestDto, account);
        sendEvent(transactionRequestDto, account);
        LOGGER.info("updated account, old {}, current {}", account, account);
        return mapToAccountDto(account);
    }

    private void sendEvent(TransactionRequestDto transactionRequestDto, AccountEntity account) {
        LOGGER.info("will send event");
        Message<AccountTransactedDto> message = prepareTransactionMessage(transactionRequestDto, account);
        MessageChannel messageChannel = accountStream.accountTransactedChannel();
        messageChannel.send(message);
        LOGGER.info("sent event {}", message);
    }

    private Message<AccountTransactedDto> prepareTransactionMessage(TransactionRequestDto transactionRequestDto, AccountEntity account) {
        return MessageBuilder
                .withPayload(prepareTransactionDto(transactionRequestDto, account))
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build();
    }

    private AccountTransactedDto prepareTransactionDto(TransactionRequestDto transactionRequestDto, AccountEntity account) {
        return AccountTransactedDto.builder()
                .accountId(transactionRequestDto.getAccountId())
                .currentBalance(account.getBalance())
                .amount(transactionRequestDto.getAmount())
                .type(transactionRequestDto.getType())
                .timestamp(LocalDateTime.now())
                .build();
    }

    private AccountEntity retrieveAccount(Long id) {
        String exceptionMessage = String.format("No account found with given id '%s'", id);
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(exceptionMessage));
    }

    private AccountSummaryDto mapToAccountDto(AccountEntity accountEntity) {
        return AccountSummaryDto.builder()
                .accountId(accountEntity.getId())
                .accountBalance(accountEntity.getBalance())
                .build();
    }

    private AccountEntity updateAccountBalance(Long id, TransactionRequestDto transactionRequestDto, AccountEntity account) {
        if (transactionRequestDto.getType().equals(TransactionType.DEPOSIT)) {
            depositAccount(transactionRequestDto, account);
        } else {
            withdrawFromAccount(id, transactionRequestDto, account);
        }
        return accountRepository.save(account);
    }

    private void depositAccount(TransactionRequestDto transactionRequestDto, AccountEntity account) {
        BigDecimal newBalance = account.getBalance()
                .add(transactionRequestDto.getAmount());
        account.setBalance(newBalance);
        LOGGER.info("deposited account with {} from the account {}", account, newBalance);
    }

    private void withdrawFromAccount(Long id, TransactionRequestDto transactionRequestDto, AccountEntity account) {
        checkAccountFunds(id, transactionRequestDto, account);
        BigDecimal newAccountBalance = account.getBalance()
                .subtract(transactionRequestDto.getAmount());
        account.setBalance(newAccountBalance);
        LOGGER.info("withdrawn {} from the account {}", newAccountBalance, account);
    }

    private void checkAccountFunds(Long id, TransactionRequestDto transactionRequestDto, AccountEntity account) {
        if (AccountHasInsufficientFunds(transactionRequestDto, account)) {
            String exceptionMessage = String.format("Account id '%s' has insufficient funds '%.2f' to deduce '%.2f'", id, account.getBalance(), transactionRequestDto.getAmount());
            throw new AccountHasInsufficientFundsException(exceptionMessage);
        }
    }

    private boolean AccountHasInsufficientFunds(TransactionRequestDto transactionRequestDto, AccountEntity account) {
        return account.getBalance()
                .subtract(transactionRequestDto.getAmount())
                .compareTo(BigDecimal.ZERO) < 0;
    }
}
