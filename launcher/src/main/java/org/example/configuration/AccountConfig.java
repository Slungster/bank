/*package org.example.configuration;

import org.example.adapters.AccountJpaAdapter;
import org.example.ports.api.AccountServicePort;
import org.example.ports.api.ClientServicePort;
import org.example.ports.spi.AccountPersistencePort;
import org.example.service.AccountServiceImpl;
import org.example.service.ClientServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ ClientConfig.class})
public class AccountConfig {

    @Bean
    public AccountPersistencePort accountPersistence(){
        return new AccountJpaAdapter();
    }

}*/
