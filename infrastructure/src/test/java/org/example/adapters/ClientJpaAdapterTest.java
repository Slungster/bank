package org.example.adapters;

import org.example.repositories.ClientRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ClientJpaAdapterTest {

    @InjectMocks
    private ClientJpaAdapter clientJpaAdapter;

    @Mock
    private ClientRepository clientRepository;

    @Test
    public void should_create_one_client () {}
}
