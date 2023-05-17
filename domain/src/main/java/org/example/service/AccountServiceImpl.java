package org.example.service;

import org.example.data.AccountDto;
import org.example.data.ClientDto;
import org.example.data.MovementDto;
import org.example.data.MovementStatusEnum;
import org.example.data.MovementTypeEnum;
import org.example.ports.api.AccountServicePort;
import org.example.ports.spi.AccountPersistencePort;
import org.example.ports.spi.ClientPersistencePort;
import org.example.ports.spi.MovementPersistencePort;

import java.util.NoSuchElementException;

public class AccountServiceImpl implements AccountServicePort {

    private AccountPersistencePort accountPersistencePort;
    private ClientPersistencePort clientPersistencePort;
    private MovementPersistencePort movementPersistencePort;

    public AccountServiceImpl(AccountPersistencePort accountPersistencePort, ClientPersistencePort clientPersistencePort, MovementPersistencePort movementPersistencePort) {
        this.accountPersistencePort = accountPersistencePort;
        this.clientPersistencePort = clientPersistencePort;
        this.movementPersistencePort = movementPersistencePort;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        validateAccountToCreate(accountDto);
        return accountPersistencePort.createAccount(accountDto);
    }

    @Override
    public AccountDto updateAccount(AccountDto accountDto) {
        validateAccountToUpdate(accountDto);
        return accountPersistencePort.updateAccount(accountDto);
    }

    @Override
    public AccountDto creditAccount(Long accountId, double amount) {
        AccountDto account = accountPersistencePort.getAccountById(accountId);
        if (account == null) {
            throw new NoSuchElementException("Le compte avec l'id " + accountId + " n'existe pas");
        }
        MovementDto movement = new MovementDto();
        movement.setAccountId(account.getId());
        movement.setClientId(account.getIdClient());
        movement.setOldBalance(account.getBalance());
        movement.setMovementType(MovementTypeEnum.CREDIT);
        movement.setMovementAmount(amount);
        movement.setMovementStatus(MovementStatusEnum.SUCCESS);
        account.creditBalance(amount);
        movement.setNewBalance(account.getBalance());

        movementPersistencePort.createMovement(movement);

        return updateAccount(account);
    }

    @Override
    public AccountDto debitAccount(Long accountId, double amount) {
        AccountDto account = accountPersistencePort.getAccountById(accountId);
        if (account == null) {
            throw new NoSuchElementException("Le compte avec l'id " + accountId + " n'existe pas");
        }
        if (account.hasSufficientBalance(amount)) {
            MovementDto movement = new MovementDto();
            movement.setAccountId(account.getId());
            movement.setClientId(account.getIdClient());
            movement.setOldBalance(account.getBalance());
            movement.setMovementType(MovementTypeEnum.DEBIT);
            movement.setMovementAmount(amount);
            movement.setMovementStatus(MovementStatusEnum.SUCCESS);
            account.debitBalance(amount);
            movement.setNewBalance(account.getBalance());

            movementPersistencePort.createMovement(movement);
            return updateAccount(account);
        }
        else {
            MovementDto movement = new MovementDto();
            movement.setAccountId(account.getId());
            movement.setClientId(account.getIdClient());
            movement.setOldBalance(account.getBalance());
            movement.setMovementType(MovementTypeEnum.DEBIT);
            movement.setMovementAmount(amount);
            movement.setMovementStatus(MovementStatusEnum.FAILURE);
            movement.setNewBalance(account.getBalance());
            
            movementPersistencePort.createMovement(movement);
            throw new IllegalArgumentException("Solde insuffisant pour le compte avec l'id " + accountId);
        }
    }

    @Override
    public AccountDto getAccountById(Long accountId) {
        return accountPersistencePort.getAccountById(accountId);
    }

    @Override
    public double getAccountBalance(Long accountId) {
        AccountDto account = accountPersistencePort.getAccountById(accountId);
        if (account == null) {
            throw new NoSuchElementException("Le compte avec l'id " + accountId + " n'existe pas");
        }
        return account.getBalance();
    }

    @Override
    public void deleteAccount(Long accountId) {
        AccountDto account = accountPersistencePort.getAccountById(accountId);
        if (account == null) {
            throw new NoSuchElementException("Le compte avec l'id " + accountId + " n'existe pas");
        }
        accountPersistencePort.deleteAccount(accountId);
    }

    private void validateAccountToCreate(AccountDto accountDto) throws IllegalArgumentException {
        if (accountDto.getId() != null) {
            throw new IllegalArgumentException("Un nouveau compte ne doit pas avoir d'identifiant");
        }
        if (accountDto.getIdClient() == null) {
            throw new IllegalArgumentException("Il faut indiquer l'id du client pour lequel on veut créer le compte");
        }

        ClientDto clientDto = clientPersistencePort.getClientById(accountDto.getIdClient());
        if (clientDto == null) {
            throw new NoSuchElementException("Le client avec l'id " + accountDto.getIdClient() + " n'existe pas, impossible de créer un compte");
        }
    }

    private void validateAccountToUpdate(AccountDto accountDto) throws IllegalArgumentException {
        if (accountDto.getId() == null) {
            throw new IllegalArgumentException("L'id du compte ne peut pas être nul");
        }
    }
}
