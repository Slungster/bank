package org.example.mappers;

import org.example.data.MovementDto;
import org.example.entities.Movement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovementMapper {

    public static final MovementMapper INSTANCE = new MovementMapper();

    public MovementMapper() {
    }

    public Movement dtoToEntity (MovementDto movementDto) {
        if (movementDto != null) {
            Movement movement = new Movement();
            movement.setId(movementDto.getId());
            movement.setOldBalance(movementDto.getOldBalance());
            movement.setMovementType(movementDto.getMovementType());
            movement.setMovementAmount(movementDto.getMovementAmount());
            movement.setMovementStatus(movementDto.getMovementStatus());
            movement.setNewBalance(movementDto.getNewBalance());
            return movement;
        }
        return null;
    }

    public MovementDto entityToDto (Movement movement) {
        if (movement != null) {
            MovementDto movementDto = new MovementDto();
            movementDto.setId(movement.getId());
            movementDto.setAccountId(movement.getAccount().getId());
            movementDto.setClientId(movement.getAccount().getClient().getId());
            movementDto.setOldBalance(movement.getOldBalance());
            movementDto.setMovementType(movement.getMovementType());
            movementDto.setMovementAmount(movement.getMovementAmount());
            movementDto.setMovementStatus(movement.getMovementStatus());
            movementDto.setNewBalance(movement.getNewBalance());
            return movementDto;
        }
        return null;
    }

    public List<MovementDto> listEntitiesToListDtos (List<Movement> movementList) {
        List<MovementDto> listDtos = new ArrayList<>();
        if (movementList != null) {
            listDtos =  movementList.stream().map(m -> entityToDto(m)).collect(Collectors.toList());

        }
        return listDtos;
    }
}
