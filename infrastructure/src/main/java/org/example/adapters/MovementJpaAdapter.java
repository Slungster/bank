package org.example.adapters;

import org.example.data.MovementDto;
import org.example.entities.Account;
import org.example.entities.Movement;
import org.example.mappers.MovementMapper;
import org.example.ports.spi.MovementPersistencePort;
import org.example.repositories.AccountRepository;
import org.example.repositories.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementJpaAdapter implements MovementPersistencePort {

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public MovementDto createMovement(MovementDto movementDto) {
        Movement movement = MovementMapper.INSTANCE.dtoToEntity(movementDto);
        Account account = accountRepository.getOne(movementDto.getAccountId());
        movement.setAccount(account);
        Movement movementCreated = movementRepository.save(movement);
        return MovementMapper.INSTANCE.entityToDto(movementCreated);
    }

    @Override
    public List<MovementDto> getMovementsByAccountId(Long accountId) {
        List<Movement> movements = movementRepository.findAllByAccountId(accountId);
        return MovementMapper.INSTANCE.listEntitiesToListDtos(movements);
    }

}
