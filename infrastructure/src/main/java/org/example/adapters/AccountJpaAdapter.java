package org.example.adapters;

import org.example.data.AccountDto;
import org.example.entities.Account;
import org.example.entities.Client;
import org.example.mappers.AccountMapper;
import org.example.ports.spi.AccountPersistencePort;
import org.example.repositories.AccountRepository;
import org.example.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountJpaAdapter implements AccountPersistencePort {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.INSTANCE.dtoToEntity(accountDto);
        Client owner = clientRepository.getOne(accountDto.getIdClient());
        account.setClient(owner);
        Account createdAccount = accountRepository.save(account);
        return AccountMapper.INSTANCE.entityToDto(createdAccount);
    }

    @Override
    public AccountDto updateAccount(AccountDto accountDto) {
        Account account = AccountMapper.INSTANCE.dtoToEntity(accountDto);
        Client owner = clientRepository.getOne(accountDto.getIdClient());
        account.setClient(owner);
        Account updatedAccount = accountRepository.save(account);
        return AccountMapper.INSTANCE.entityToDto(updatedAccount);
    }

    @Override
    public AccountDto getAccountById(Long accountId) {
        Optional<Account> accountEntity = accountRepository.findById(accountId);
        if(accountEntity.isPresent()) {
            return AccountMapper.INSTANCE.entityToDto(accountEntity.get());
        }
        return null;
    }

    @Override
    public double getAccountBalance(Long accountId) {
        return getAccountById(accountId).getBalance();
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);

    }
}
