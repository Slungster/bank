package org.example.mappers;

import org.assertj.core.api.Assertions;
import org.example.data.MovementDto;
import org.example.data.MovementStatusEnum;
import org.example.data.MovementTypeEnum;
import org.example.entities.Account;
import org.example.entities.Client;
import org.example.entities.Movement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MovementMapperTest {

    private Client client;
    private Account account;

    private Movement movementOne;
    private Movement movementTwo;

    private MovementDto movementDto;

    private List<Movement> movementList = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        client = new Client(25L,"Premier","François","75002");
        account = new Account(1L,500, client);

        movementOne = new Movement();
        movementOne.setId(2L);
        movementOne.setOldBalance(125.25);
        movementOne.setMovementType(MovementTypeEnum.CREDIT);
        movementOne.setMovementAmount(200.75);
        movementOne.setMovementStatus(MovementStatusEnum.SUCCESS);
        movementOne.setNewBalance(326);
        movementOne.setAccount(account);

        movementTwo = new Movement();
        movementTwo.setId(3L);
        movementTwo.setOldBalance(80.90);
        movementTwo.setMovementType(MovementTypeEnum.DEBIT);
        movementTwo.setMovementAmount(100);
        movementTwo.setMovementStatus(MovementStatusEnum.FAILURE);
        movementTwo.setNewBalance(80.90);
        movementTwo.setAccount(account);

        movementList.add(movementOne);
        movementList.add(movementTwo);

        movementDto = new MovementDto();
        movementDto.setId(41L);
        movementDto.setOldBalance(900.50);
        movementDto.setMovementType(MovementTypeEnum.DEBIT);
        movementDto.setMovementAmount(150);
        movementDto.setMovementStatus(MovementStatusEnum.SUCCESS);
        movementDto.setNewBalance(750.50);
    }

    @Test
    public void should_transform_dto_to_entity () {
        Movement movement = MovementMapper.INSTANCE.dtoToEntity(movementDto);
        Assertions.assertThat(movement.getId()).isEqualTo(movementDto.getId());
        Assertions.assertThat(movement.getOldBalance()).isEqualTo(movementDto.getOldBalance());
        Assertions.assertThat(movement.getMovementType()).isEqualTo(movementDto.getMovementType());
        Assertions.assertThat(movement.getMovementAmount()).isEqualTo(movementDto.getMovementAmount());
        Assertions.assertThat(movement.getMovementStatus()).isEqualTo(movementDto.getMovementStatus());
        Assertions.assertThat(movement.getNewBalance()).isEqualTo(movementDto.getNewBalance());

    }

    @Test
    public void should_transform_null_dto_to_null_entity () {
        Movement movement = MovementMapper.INSTANCE.dtoToEntity(null);
        Assertions.assertThat(movement).isNull();

    }

    @Test
    public void should_transform_entity_to_dto () {
        MovementDto movementDto = MovementMapper.INSTANCE.entityToDto(movementTwo);
        Assertions.assertThat(movementDto.getId()).isEqualTo(movementTwo.getId());
        Assertions.assertThat(movementDto.getOldBalance()).isEqualTo(movementTwo.getOldBalance());
        Assertions.assertThat(movementDto.getMovementType()).isEqualTo(movementTwo.getMovementType());
        Assertions.assertThat(movementDto.getMovementAmount()).isEqualTo(movementTwo.getMovementAmount());
        Assertions.assertThat(movementDto.getMovementStatus()).isEqualTo(movementTwo.getMovementStatus());
        Assertions.assertThat(movementDto.getNewBalance()).isEqualTo(movementTwo.getNewBalance());

    }

    @Test
    public void should_transform_null_entity_to_null_dto () {
        MovementDto movementDto = MovementMapper.INSTANCE.entityToDto(null);
        Assertions.assertThat(movementDto).isNull();

    }

    @Test
    public void should_transform_listEntities_to_listDtos () {
        List<MovementDto> movementDtoList = MovementMapper.INSTANCE.listEntitiesToListDtos(movementList);
        Assertions.assertThat(movementDtoList).hasSize(2);
        Assertions.assertThat(
                movementDtoList.stream().filter(m -> m.getId().equals(2L)).findFirst().get().getNewBalance())
                .isEqualTo(326);
        Assertions.assertThat(
                movementDtoList.stream().filter(m -> m.getId().equals(3L)).findFirst().get().getMovementAmount())
                .isEqualTo(100);
    }

}
