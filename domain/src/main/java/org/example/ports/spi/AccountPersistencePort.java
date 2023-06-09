package org.example.ports.spi;

import org.example.data.AccountDto;

public interface AccountPersistencePort {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto updateAccount(AccountDto accountDto);

    AccountDto getAccountById (Long accountId);

    double getAccountBalance(Long accountId);

    void deleteAccount(Long accountId);
}
