package org.example.mappers;

import org.assertj.core.api.Assertions;
import org.example.data.ClientDto;
import org.example.entities.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ClientMapperTest {

    private Client clientOne;
    private Client clientTwo;

    private ClientDto clientDto;

    private List<Client> clientList = new ArrayList<>();

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
        Client client = ClientMapper.INSTANCE.dtoToEntity(clientDto);
        Assertions.assertThat(client.getId()).isEqualTo(clientDto.getId());
        Assertions.assertThat(client.getFirstname()).isEqualTo(clientDto.getFirstname());
        Assertions.assertThat(client.getLastname()).isEqualTo(clientDto.getLastname());
        Assertions.assertThat(client.getZipcode()).isEqualTo(clientDto.getZipcode());
    }

    @Test
    public void should_transform_null_dto_to_null_entity () {
        Client client = ClientMapper.INSTANCE.dtoToEntity(null);
        Assertions.assertThat(client).isNull();
    }

    @Test
    public void should_transform_entity_to_dto () {
        ClientDto clientDto = ClientMapper.INSTANCE.entityToDto(clientTwo);
        Assertions.assertThat(clientDto.getId()).isEqualTo(clientTwo.getId());
        Assertions.assertThat(clientDto.getFirstname()).isEqualTo(clientTwo.getFirstname());
        Assertions.assertThat(clientDto.getLastname()).isEqualTo(clientTwo.getLastname());
        Assertions.assertThat(clientDto.getZipcode()).isEqualTo(clientTwo.getZipcode());
    }

    @Test
    public void should_transform_null_entity_to_null_dto () {
        ClientDto clientDto = ClientMapper.INSTANCE.entityToDto(null);
        Assertions.assertThat(clientDto).isNull();
    }

    @Test
    public void should_transform_listEntities_to_listDtos () {
        List<ClientDto> clientDtoList = ClientMapper.INSTANCE.listEntitiesToListDtos(clientList);
        Assertions.assertThat(clientDtoList).hasSize(2);
        Assertions.assertThat(
                clientDtoList.stream().filter(c -> c.getId().equals(25L)).findFirst().get().getZipcode())
                .isEqualTo("75002");
        Assertions.assertThat(
                clientDtoList.stream().filter(c -> c.getLastname().equals("Elizabeth")).findFirst().get().getId())
                .isEqualTo(38L);
    }
}
