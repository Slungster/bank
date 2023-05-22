package org.example.adapters;

import org.example.data.MovementDto;
import org.example.data.MovementStatusEnum;
import org.example.data.MovementTypeEnum;
import org.example.entities.Account;
import org.example.entities.Movement;
import org.example.mappers.MovementMapper;
import org.example.repositories.AccountRepository;
import org.example.repositories.MovementRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MovementJpaAdapterTest {

    @InjectMocks
    private MovementJpaAdapter movementJpaAdapter;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private MovementRepository movementRepository;

    @Test
    public void should_create_one_movement () {

        Account account = new Account(1L, 890.65, null);
        Movement movement = new Movement();
        movement.setMovementAmount(350);

        when(accountRepository.getOne(null)).thenReturn(account);
        when(movementRepository.save(any(Movement.class))).thenReturn(movement);

        MovementDto actual = movementJpaAdapter.createMovement(MovementMapper.INSTANCE.entityToDto(movement));

        assertThat(actual).usingRecursiveComparison().isEqualTo(MovementMapper.INSTANCE.entityToDto(movement));
        verify(movementRepository, times(1)).save(any(Movement.class));
        verifyNoMoreInteractions(movementRepository);
    }

    @Test
    public void should_return_list_of_movements_for_one_account () {

        Movement movementOne = new Movement();
        movementOne.setId(25L);
        movementOne.setMovementType(MovementTypeEnum.CREDIT);
        movementOne.setMovementStatus(MovementStatusEnum.SUCCESS);
        movementOne.setOldBalance(520);

        Movement movementTwo = new Movement();
        movementTwo.setId(26L);
        movementTwo.setMovementType(MovementTypeEnum.DEBIT);
        movementTwo.setMovementStatus(MovementStatusEnum.FAILURE);
        movementTwo.setNewBalance(740);

        when(movementRepository.findAllByAccountId(anyLong())).thenReturn(List.of(movementOne, movementTwo));

        assertThat(movementJpaAdapter.getMovementsByAccountId(anyLong())).hasSize(2);
        assertThat(movementJpaAdapter.getMovementsByAccountId(anyLong())
                .stream().filter(m -> m.getId().equals(25L)).findFirst().get().getMovementStatus())
                .isEqualTo(MovementStatusEnum.SUCCESS);
        assertThat(movementJpaAdapter.getMovementsByAccountId(anyLong())
                .stream().filter(m -> m.getMovementType().equals(MovementTypeEnum.DEBIT)).findFirst().get().getId())
                .isEqualTo(26L);
        verify(movementRepository, times(3)).findAllByAccountId(anyLong());
        verifyNoMoreInteractions(movementRepository);
    }

    @Test
    public void should_return_empty_list_if_no_movements () {

        when(movementRepository.findAllByAccountId(anyLong())).thenReturn(null);

        assertThat(movementJpaAdapter.getMovementsByAccountId(anyLong())).isNotNull();
        assertThat(movementJpaAdapter.getMovementsByAccountId(anyLong())).hasSize(0);
        verify(movementRepository, times(2)).findAllByAccountId(anyLong());
        verifyNoMoreInteractions(movementRepository);
    }
}
