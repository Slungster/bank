package org.example.service;

import org.example.data.AccountDto;
import org.example.data.MovementDto;
import org.example.ports.api.MovementServicePort;
import org.example.ports.spi.AccountPersistencePort;
import org.example.ports.spi.MovementPersistencePort;

import java.util.List;
import java.util.NoSuchElementException;

public class MovementServiceImpl implements MovementServicePort {

    private MovementPersistencePort movementPersistencePort;
    private AccountPersistencePort accountPersistencePort;

    public MovementServiceImpl(MovementPersistencePort movementPersistencePort, AccountPersistencePort accountPersistencePort) {
        this.movementPersistencePort = movementPersistencePort;
        this.accountPersistencePort = accountPersistencePort;
    }

    @Override
    public MovementDto createMovement(MovementDto movementDto) {
        validateMovementToCreate(movementDto);
        return movementPersistencePort.createMovement(movementDto);
    }

    @Override
    public List<MovementDto> getMovementByAccountId(Long accountId) {

        if (accountId == null) {
            throw new IllegalArgumentException("L'id du compte est obligatoire");
        }

        AccountDto accountDto = accountPersistencePort.getAccountById(accountId);
        if (accountDto == null) {
            throw new NoSuchElementException("Le compte avec l'id " + accountId + " n'existe pas");
        }

        return movementPersistencePort.getMovementsByAccountId(accountId);
    }

    public void validateMovementToCreate (MovementDto movementDto) {

        if (movementDto.getId() != null) {
            throw new IllegalArgumentException("Un mouvement d'argent à créer ne peut pas avoir d'id");
        }
    }
}
