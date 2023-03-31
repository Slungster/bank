package org.example.mappers;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.example.data.ClientDto;
import org.example.entities.Client;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

public class ClientMapperTest {

   /* private Client clientOne;
    private Client clientTwo;

    private ClientDto clientDto;

    private List<Client> clientList = new ArrayList<>();

    @InjectMocks
    private ClientMapper clientMapper;

    @BeforeEach
    public void setUp() {
        clientOne = new Client(25L,"Premier","François","75002");
        clientTwo = new Client(38L,"Deux","Elizabeth","26003");

        clientDto = new ClientDto(89L,"Trois","Charles","76800");

        clientList.add(clientOne);
        clientList.add(clientTwo);
    }

    @Test
    public void should_transform_dto_to_entity () {
        Client client = clientMapper.dtoToEntity(clientDto);
        Assertions.assertThat(client.getId()).isEqualTo(clientDto.getId());
        Assertions.assertThat(client.getFirstname()).isEqualTo(clientDto.getFirstname());
        Assertions.assertThat(client.getLastname()).isEqualTo(clientDto.getLastname());
        Assertions.assertThat(client.getZipcode()).isEqualTo(clientDto.getZipcode());
    }

    @Test
    public void should_transform_entity_to_dto () {
        ClientDto clientDto = clientMapper.entityToDto(clientTwo);
        Assertions.assertThat(clientDto.getId()).isEqualTo(clientTwo.getId());
        Assertions.assertThat(clientDto.getFirstname()).isEqualTo(clientTwo.getFirstname());
        Assertions.assertThat(clientDto.getLastname()).isEqualTo(clientTwo.getLastname());
        Assertions.assertThat(clientDto.getZipcode()).isEqualTo(clientTwo.getZipcode());
    }

    @Test
    public void should_transform_listEntities_to_listDtos () {
        List<ClientDto> clientDtoList = clientMapper.listEntitiesToListDtos(clientList);
        Assertions.assertThat(clientDtoList).hasSize(2);
    }*/
}
