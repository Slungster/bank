package org.example.ports.spi;

import org.example.data.MovementDto;

import java.util.List;

public interface MovementPersistencePort {

    MovementDto createMovement(MovementDto movementDto);

    List<MovementDto> getMovementByAccountId (Long accountId);
}
