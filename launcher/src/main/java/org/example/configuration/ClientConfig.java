/*package org.example.configuration;

import org.example.adapters.ClientJpaAdapter;
import org.example.ports.api.ClientServicePort;
import org.example.ports.spi.ClientPersistencePort;
import org.example.service.ClientServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public ClientPersistencePort clientPersistence(){
        return new ClientJpaAdapter();
    }

    @Bean
    public ClientServicePort clientService(){
        return new ClientServiceImpl(clientPersistence());
    }
}*/
