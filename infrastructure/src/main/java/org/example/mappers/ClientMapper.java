package org.example.mappers;

import org.example.data.ClientDto;
import org.example.entities.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientMapper {

    public static final ClientMapper INSTANCE = new ClientMapper();

    public ClientMapper() {
    }

    public Client dtoToEntity (ClientDto clientDto) {
        if (clientDto != null) {
            //return new Client(clientDto.getId(), clientDto.getFirstname(), clientDto.getLastname(), clientDto.getZipcode());
            Client client = new Client();
            client.setId(clientDto.getId());
            client.setFirstname(clientDto.getFirstname());
            client.setLastname(clientDto.getLastname());
            client.setZipcode(clientDto.getZipcode());
            return client;
        }
        return null;
    }

    public ClientDto entityToDto (Client client) {
        if (client != null) {
            //return new ClientDto(client.getId(), client.getFirstname(), client.getLastname(), client.getZipcode());
            ClientDto clientDto = new ClientDto();
            clientDto.setId(client.getId());
            clientDto.setFirstname(client.getFirstname());
            clientDto.setLastname(client.getLastname());
            clientDto.setZipcode(client.getZipcode());
            return clientDto;
        }
        return null;
    }

    public List<ClientDto> listEntitiesToListDtos (List<Client> listClients) {

        List<ClientDto> listDtos = new ArrayList<>();

        if (listClients != null) {
            listDtos = listClients.stream().map(c -> entityToDto(c)).collect(Collectors.toList());
        }

        return listDtos;

    }
}
