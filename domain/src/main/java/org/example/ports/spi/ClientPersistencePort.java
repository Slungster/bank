package org.example.ports.spi;

import org.example.data.ClientDto;

import java.util.List;

public interface ClientPersistencePort {

    ClientDto createClient (ClientDto clientDto);

    ClientDto updateClient (ClientDto clientDto);

    void deleteClientById (Long id);

    ClientDto getClientById (Long id);

    List<ClientDto> getClients ();
}
