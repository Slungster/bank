package org.example.adapters;

import org.example.data.AccountDto;
import org.example.data.ClientDto;
import org.example.entities.Account;
import org.example.mappers.AccountMapper;
import org.example.mappers.ClientMapper;
import org.example.repositories.AccountRepository;
import org.example.repositories.ClientRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class AccountJpaAdapterTest {

    @InjectMocks
    private AccountJpaAdapter accountJpaAdapter;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ClientRepository clientRepository;

    @Test
    public void should_create_account_for_a_client () {

        final ClientDto owner = new ClientDto(1L,"Philip","Morris","45200");
        final AccountDto accountToCreate = new AccountDto();
        accountToCreate.setBalance(250.86);
        when(clientRepository.getOne(null)).thenReturn(ClientMapper.INSTANCE.dtoToEntity(owner));
        when(accountRepository.save(any(Account.class))).thenReturn(AccountMapper.INSTANCE.dtoToEntity(accountToCreate));

        final AccountDto actual = accountJpaAdapter.createAccount(accountToCreate);

        assertThat(actual).usingRecursiveComparison().isEqualTo(accountToCreate);
        assertThat(actual).extracting("balance").isEqualTo(250.86);
        verify(clientRepository, times(1)).getOne(null);
        verify(accountRepository, times(1)).save(any(Account.class));
        verifyNoMoreInteractions(clientRepository);
        verifyNoMoreInteractions(accountRepository);
    }

    @Test
    public void should_update_account_for_a_client () {

        final ClientDto owner = new ClientDto(1L,"John","Doe","78000");
        final AccountDto accountToUpdate = new AccountDto();
        accountToUpdate.setBalance(856.23);
        when(clientRepository.getOne(null)).thenReturn(ClientMapper.INSTANCE.dtoToEntity(owner));
        when(accountRepository.save(any(Account.class))).thenReturn(AccountMapper.INSTANCE.dtoToEntity(accountToUpdate));

        final AccountDto actual = accountJpaAdapter.updateAccount(accountToUpdate);

        assertThat(actual).usingRecursiveComparison().isEqualTo(accountToUpdate);
        assertThat(actual).extracting("balance").isEqualTo(856.23);
        verify(clientRepository, times(1)).getOne(null);
        verify(accountRepository, times(1)).save(any(Account.class));
        verifyNoMoreInteractions(clientRepository);
        verifyNoMoreInteractions(accountRepository);
    }

    @Test
    public void should_return_one_account_by_his_id () {

        final AccountDto accountToFind = new AccountDto();
        accountToFind.setId(98L);
        accountToFind.setBalance(630.25);

        when(accountRepository.findById(anyLong())).thenReturn(Optional.ofNullable(AccountMapper.INSTANCE.dtoToEntity(accountToFind)));

        final AccountDto actual = accountJpaAdapter.getAccountById(anyLong());

        assertThat(actual).usingRecursiveComparison().isEqualTo(accountToFind);
        assertThat(actual).extracting("id").isEqualTo(98L);
        assertThat(actual).extracting("balance").isEqualTo(630.25);
        verify(accountRepository, times(1)).findById(anyLong());
        verifyNoMoreInteractions(accountRepository);
    }

    @Test
    public void should_not_return_an_account_that_doesnt_exist () {

        when(accountRepository.findById(anyLong())).thenReturn(Optional.empty());

        final AccountDto actual = accountJpaAdapter.getAccountById(anyLong());

        assertThat(actual).usingRecursiveComparison().isNull();
        verify(accountRepository, times(1)).findById(anyLong());
        verifyNoMoreInteractions(accountRepository);
    }

    @Test
    public void should_return_account_balance () {

        final AccountDto accountToFind = new AccountDto();
        accountToFind.setId(14L);
        accountToFind.setBalance(1250.65);

        when(accountRepository.findById(anyLong())).thenReturn(Optional.ofNullable(AccountMapper.INSTANCE.dtoToEntity(accountToFind)));

        final double actualBalance = accountJpaAdapter.getAccountBalance(anyLong());

        assertThat(actualBalance).isEqualTo(1250.65);
        verify(accountRepository, times(1)).findById(anyLong());
        verifyNoMoreInteractions(accountRepository);
    }

/*    @Test
    public void should_delete_one_account () {

        doNothing().when(accountRepository).deleteById(anyLong());

        accountJpaAdapter.deleteAccount(anyLong());

        verify(accountRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(accountRepository);
    }*/

}
