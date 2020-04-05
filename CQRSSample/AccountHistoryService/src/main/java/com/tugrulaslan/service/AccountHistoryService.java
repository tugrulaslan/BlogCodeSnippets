package com.tugrulaslan.service;

import com.tugrulaslan.dto.AccountDto;
import com.tugrulaslan.dto.AccountHistoryDto;
import com.tugrulaslan.dto.AccountTransactedDto;
import com.tugrulaslan.dto.TransactionType;
import com.tugrulaslan.entity.AccountEntity;
import com.tugrulaslan.entity.AccountHistoryEntity;
import com.tugrulaslan.exception.AccountNotFoundException;
import com.tugrulaslan.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountHistoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountHistoryService.class);

    private final AccountRepository accountRepository;

    public AccountHistoryService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountDto retrieveById(Long id) {
        AccountEntity accountEntity = retrieveAccount(id);
        LOGGER.info("retrieved account history entity by id " + id);
        return mapToAccountDto(accountEntity);
    }

    @Transactional
    public void handleEvent(AccountTransactedDto transactedDto) {
        AccountEntity accountEntity = retrieveAccount(transactedDto.getAccountId());
        updateAccountBalance(transactedDto, accountEntity);
    }

    private void updateAccountBalance(AccountTransactedDto transactedDto, AccountEntity account) {
        if (transactedDto.getType().equals(TransactionType.DEPOSIT)) {
            depositAccount(transactedDto, account);
        } else {
            withdrawFromAccount(transactedDto, account);
        }
        addAccountHistory(transactedDto, account);
        accountRepository.save(account);
    }

    private AccountEntity retrieveAccount(Long id) {
        String exceptionMessage = String.format("No account found with given id '%s'", id);
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(exceptionMessage));
    }

    private AccountDto mapToAccountDto(AccountEntity accountEntity) {
        return AccountDto.builder()
                .id(accountEntity.getId())
                .balance(accountEntity.getBalance())
                .history(mapToAccountHistoryList(accountEntity))
                .build();
    }

    private List<AccountHistoryDto> mapToAccountHistoryList(AccountEntity accountEntity) {
        return accountEntity.getAccountHistory().stream()
                .map(this::mapToAccountHistoryDto)
                .collect(Collectors.toList());
    }

    private AccountHistoryDto mapToAccountHistoryDto(AccountHistoryEntity entity) {
        return AccountHistoryDto.builder()
                .amount(entity.getBalance())
                .type(entity.getTransactionType())
                .createdDateTime(entity.getCreatedDateTime())
                .build();
    }

    private void addAccountHistory(AccountTransactedDto transactedDto, AccountEntity account) {
        AccountHistoryEntity historyEntity = prepareAccountHistoryEntity(transactedDto, account);
        account.getAccountHistory().add(historyEntity);
        LOGGER.info("added account history {} to the account", historyEntity);
    }

    private AccountHistoryEntity prepareAccountHistoryEntity(AccountTransactedDto transactedDto, AccountEntity account) {
        AccountHistoryEntity accountHistoryEntity = new AccountHistoryEntity();
        accountHistoryEntity.setBalance(transactedDto.getBalance());
        accountHistoryEntity.setTransactionType(transactedDto.getType());
        accountHistoryEntity.setCreatedDateTime(transactedDto.getTimestamp());
        accountHistoryEntity.setAccount(account);

        return accountHistoryEntity;
    }

    private void depositAccount(AccountTransactedDto transactedDto, AccountEntity account) {
        BigDecimal newBalance = account.getBalance()
                .add(transactedDto.getBalance());
        account.setBalance(newBalance);
        LOGGER.info("deposited account with {} from the account {}", account, newBalance);
    }

    private void withdrawFromAccount(AccountTransactedDto transactedDto, AccountEntity account) {
        BigDecimal newAccountBalance = account.getBalance()
                .subtract(transactedDto.getBalance());
        account.setBalance(newAccountBalance);
        LOGGER.info("withdrawn {} from the account {} new balance is {}", transactedDto.getBalance(), account, newAccountBalance);
    }
}
