package org.example.ports.api;

import org.example.data.AccountDto;

public interface AccountServicePort {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto updateAccount(AccountDto accountDto);

    AccountDto creditAccount(Long accountId, double amount);

    AccountDto debitAccount(Long accountId, double amount);

    AccountDto getAccountById (Long accountId);

    double getAccountBalance(Long accountId);

    void deleteAccount(Long accountId);
}
