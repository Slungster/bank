package org.example.adapters;

import org.example.data.ClientDto;
import org.example.entities.Client;
import org.example.mappers.ClientMapper;
import org.example.ports.spi.ClientPersistencePort;
import org.example.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientJpaAdapter implements ClientPersistencePort {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;


    @Override
    public ClientDto createClient(ClientDto clientDto) {
        Client client = clientMapper.dtoToEntity(clientDto);
        Client createdClient = clientRepository.save(client);
        return clientMapper.entityToDto(createdClient);
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {
        Client client = clientMapper.dtoToEntity(clientDto);
        Client updatedClient = clientRepository.save(client);
        return clientMapper.entityToDto(updatedClient);
    }

    @Override
    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);

    }

    @Override
    public ClientDto getClientById(Long id) {
        Optional<Client> clientEntity = clientRepository.findById(id);
        if(clientEntity.isPresent()) {
            return clientMapper.entityToDto(clientEntity.get());
        }
        return null;
    }

    @Override
    public List<ClientDto> getClients() {
        List<Client> clients = clientRepository.findAll();
        if (clients != null) {
            return clientMapper.listEntitiesToListDtos(clients);
        }
        return new ArrayList<>();
    }
}
