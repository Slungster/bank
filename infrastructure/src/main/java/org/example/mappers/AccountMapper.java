package org.example.mappers;

import org.example.data.AccountDto;
import org.example.entities.Account;

public class AccountMapper {

    public static final AccountMapper INSTANCE = new AccountMapper();

    public AccountMapper() {
    }

    public Account dtoToEntity (AccountDto accountDto) {
        if (accountDto != null) {
            Account account = new Account();
            account.setId(accountDto.getId());
            account.setBalance(accountDto.getBalance());
            return account;
        }
        return null;
    }

    public AccountDto entityToDto (Account account) {
        if (account != null) {
            AccountDto accountDto = new AccountDto();
            accountDto.setId(account.getId());
            accountDto.setBalance(account.getBalance());
            if (account.getClient() != null) {
                accountDto.setIdClient(account.getClient().getId());
            }
            return accountDto;
        }
        return null;
    }
}
