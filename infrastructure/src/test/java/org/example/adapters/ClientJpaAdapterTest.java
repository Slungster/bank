package org.example.adapters;

import org.example.data.ClientDto;
import org.example.entities.Client;
import org.example.mappers.ClientMapper;
import org.example.repositories.ClientRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ClientJpaAdapterTest {

    @InjectMocks
    private ClientJpaAdapter clientJpaAdapter;

    @Mock
    private ClientRepository clientRepository;

    @Test
    public void should_create_one_client () {

        final ClientDto clientToCreate = new ClientDto(null,"Philip","Morris","45200");
        when(clientRepository.save(any(Client.class))).thenReturn(ClientMapper.INSTANCE.dtoToEntity(clientToCreate));

        final ClientDto actual = clientJpaAdapter.createClient(clientToCreate);

        assertThat(actual).usingRecursiveComparison().isEqualTo(clientToCreate);
        verify(clientRepository, times(1)).save(any(Client.class));
        verifyNoMoreInteractions(clientRepository);
    }

    @Test
    public void should_update_one_client () {

        final ClientDto clientToUpdate = new ClientDto(10L,"Issey","Miake","38500");
        when(clientRepository.save(any(Client.class))).thenReturn(ClientMapper.INSTANCE.dtoToEntity(clientToUpdate));

        final ClientDto actual = clientJpaAdapter.updateClient(clientToUpdate);

        assertThat(actual).usingRecursiveComparison().isEqualTo(clientToUpdate);
        verify(clientRepository, times(1)).save(any(Client.class));
        verifyNoMoreInteractions(clientRepository);
    }

    @Test
    public void should_delete_one_client () {

        doNothing().when(clientRepository).deleteById(anyLong());

        clientJpaAdapter.deleteClientById(anyLong());

        verify(clientRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(clientRepository);

    }

    @Test
    public void should_return_one_client_by_his_id () {

        final ClientDto clientToFind = new ClientDto(58L,"Francesco","Totti","24630");
        when(clientRepository.findById(anyLong())).thenReturn(Optional.ofNullable(ClientMapper.INSTANCE.dtoToEntity(clientToFind)));

        final ClientDto actual = clientJpaAdapter.getClientById(anyLong());

        assertThat(actual).usingRecursiveComparison().isEqualTo(clientToFind);
        verify(clientRepository, times(1)).findById(anyLong());
        verifyNoMoreInteractions(clientRepository);

    }

    @Test
    public void should_not_return_client_that_doesnt_exist () {

        when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());

        final ClientDto actual = clientJpaAdapter.getClientById(anyLong());

        assertThat(actual).usingRecursiveComparison().isNull();
        verify(clientRepository, times(1)).findById(anyLong());
        verifyNoMoreInteractions(clientRepository);

    }

    @Test
    public void should_find_and_return_all_clients () {

        Client clientOne = new Client(76L,"Charles","Quint","58960");
        Client clientTwo = new Client(77L,"Albert","Premier","01000");

        when(clientRepository.findAll()).thenReturn(List.of(clientOne, clientTwo));

        assertThat(clientJpaAdapter.getClients()).hasSize(2);
        assertThat(clientJpaAdapter.getClients()
                .stream().filter(c -> c.getId().equals(76L)).findFirst().get().getFirstname())
                .isEqualTo("Charles");
        assertThat(clientJpaAdapter.getClients()
                .stream().filter(c -> c.getLastname().equals("Premier")).findFirst().get().getId())
                .isEqualTo(77L);
        verify(clientRepository, times(3)).findAll();
        verifyNoMoreInteractions(clientRepository);

    }

    @Test
    public void should_return_empty_list_where_no_clients () {

        when(clientRepository.findAll()).thenReturn(null);

        assertThat(clientJpaAdapter.getClients()).isNotNull();
        assertThat(clientJpaAdapter.getClients()).hasSize(0);
        verify(clientRepository, times(2)).findAll();
        verifyNoMoreInteractions(clientRepository);

    }
}
