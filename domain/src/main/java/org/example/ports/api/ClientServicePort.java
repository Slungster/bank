package org.example.ports.api;

import org.example.data.ClientDto;

import java.util.List;
import java.util.NoSuchElementException;

public interface ClientServicePort {

    ClientDto createClient (ClientDto clientDto) throws IllegalArgumentException;

    ClientDto updateClient (ClientDto clientDto) throws IllegalArgumentException, NoSuchElementException;

    void deleteClientById (Long id);

    ClientDto getClientById (Long id) throws NoSuchElementException;

    List<ClientDto> getClients ();
}
