package org.example.service;

import org.example.data.ClientDto;
import org.example.ports.api.ClientServicePort;
import org.example.ports.spi.ClientPersistencePort;

import java.util.List;
import java.util.NoSuchElementException;

public class ClientServiceImpl implements ClientServicePort {

    private ClientPersistencePort clientPersistencePort;

    public ClientServiceImpl(ClientPersistencePort clientPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
    }

    @Override
    public ClientDto createClient(ClientDto clientDto) throws IllegalArgumentException {
        validateClientToCreate(clientDto);
        return clientPersistencePort.createClient(clientDto);
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) throws IllegalArgumentException, NoSuchElementException {
        validateClientToUpdate(clientDto);
        ClientDto clientToUpdate = getClientById(clientDto.getId());
        if (clientToUpdate == null) {
            throw new NoSuchElementException("Client avec id " + clientDto.getId() + " n'existe pas, mise à jour impossible");
        }
        return clientPersistencePort.updateClient(clientDto);
    }

    @Override
    public void deleteClientById(Long id) {
        ClientDto clientToUpdate = getClientById(id);
        if (clientToUpdate == null) {
            throw new NoSuchElementException("Client avec id " + id + " n'existe pas, suppression impossible");
        }
        clientPersistencePort.deleteClientById(id);
    }

    @Override
    public ClientDto getClientById(Long id) throws NoSuchElementException {
        ClientDto clientDto = clientPersistencePort.getClientById(id);
        if (clientDto == null) {
            throw new NoSuchElementException("Le client avec l'id " + id + " n'existe pas");
        }
        return clientDto;
    }

    @Override
    public List<ClientDto> getClients() {
        return clientPersistencePort.getClients();
    }

    private static void validateClientToCreate(ClientDto clientDto) throws IllegalArgumentException {
        if (clientDto.getId() != null) {
            throw new IllegalArgumentException("Un nouveau client ne doit pas avoir d'identifiant");
        }
        if (clientDto.getFirstname() == null) {
            throw new IllegalArgumentException("Firstname est obligatoire, à l'instar du lastname et du zipcode");
        }
        if (clientDto.getLastname() == null) {
            throw new IllegalArgumentException("Lastname est obligatoire, à l'instar du firstname et du zipcode");
        }
        if (clientDto.getZipcode() == null) {
            throw new IllegalArgumentException("Zipcode est obligatoire, à l'instar du lastname et du firstname");
        }
    }

    private static void validateClientToUpdate(ClientDto clientDto) throws IllegalArgumentException {
        if (clientDto.getId() == null) {
            throw new IllegalArgumentException("L'identifiant du client est obligatoire");
        }
        if (clientDto.getFirstname() == null) {
            throw new IllegalArgumentException("Firstname est obligatoire, à l'instar du lastname et du zipcode");
        }
        if (clientDto.getLastname() == null) {
            throw new IllegalArgumentException("Lastname est obligatoire, à l'instar du firstname et du zipcode");
        }
        if (clientDto.getZipcode() == null) {
            throw new IllegalArgumentException("Zipcode est obligatoire, à l'instar du lastname et du firstname");
        }
    }
}
