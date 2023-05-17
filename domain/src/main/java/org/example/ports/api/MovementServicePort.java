package org.example.ports.api;

import org.example.data.MovementDto;

import java.util.List;

public interface MovementServicePort {

    MovementDto createMovement(MovementDto movementDto);

    List<MovementDto> getMovementByAccountId (Long accountId);
}
