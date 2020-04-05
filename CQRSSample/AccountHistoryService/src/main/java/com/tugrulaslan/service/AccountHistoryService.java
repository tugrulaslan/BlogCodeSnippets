package com.tugrulaslan.service;

import com.tugrulaslan.dto.AccountHistoryDto;
import com.tugrulaslan.dto.AccountSummaryWithHistoryDto;
import com.tugrulaslan.dto.AccountTransactedDto;
import com.tugrulaslan.entity.AccountEntity;
import com.tugrulaslan.entity.AccountHistoryEntity;
import com.tugrulaslan.exception.AccountNotFoundException;
import com.tugrulaslan.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountHistoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountHistoryService.class);

    private final AccountRepository accountRepository;

    public AccountHistoryService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountSummaryWithHistoryDto retrieveById(Long id) {
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
        account.setBalance(transactedDto.getCurrentBalance());
        LOGGER.info("new account balance {}", transactedDto.getCurrentBalance());
        addAccountHistory(transactedDto, account);
        accountRepository.save(account);
        LOGGER.info("updated account with id ", transactedDto.getAccountId());
    }

    private AccountEntity retrieveAccount(Long id) {
        String exceptionMessage = String.format("No account found with given id '%s'", id);
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(exceptionMessage));
    }

    private AccountSummaryWithHistoryDto mapToAccountDto(AccountEntity accountEntity) {
        return AccountSummaryWithHistoryDto.builder()
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
                .amount(entity.getAmount())
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
        accountHistoryEntity.setAmount(transactedDto.getAmount());
        accountHistoryEntity.setTransactionType(transactedDto.getType());
        accountHistoryEntity.setCreatedDateTime(transactedDto.getTimestamp());
        accountHistoryEntity.setAccount(account);
        return accountHistoryEntity;
    }
}
