package org.example.configuration;

import org.example.adapters.AccountJpaAdapter;
import org.example.adapters.ClientJpaAdapter;
import org.example.ports.api.AccountServicePort;
import org.example.ports.api.ClientServicePort;
import org.example.ports.spi.AccountPersistencePort;
import org.example.ports.spi.ClientPersistencePort;
import org.example.service.AccountServiceImpl;
import org.example.service.ClientServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankConfig {

    @Bean
    public ClientPersistencePort clientPersistence(){
        return new ClientJpaAdapter();
    }

    @Bean
    public ClientServicePort clientService(){
        return new ClientServiceImpl(clientPersistence());
    }

    @Bean
    public AccountPersistencePort accountPersistence(){
        return new AccountJpaAdapter();
    }

    @Bean
    public AccountServicePort accountService(){
        return new AccountServiceImpl(accountPersistence(), clientPersistence());
    }
}
