package org.example.mappers;

import org.assertj.core.api.Assertions;
import org.example.data.AccountDto;
import org.example.entities.Account;
import org.example.entities.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AccountMapperTest {

    private Client client;
    private Account accountOne;
    private Account accountTwo;
    private AccountDto accountDto;
    private List<Account> accountList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        client = new Client(25L,"Premier","François","75002");

        accountOne = new Account(1L,500, client);
        accountTwo = new Account(2L,1200, client);

        accountDto = new AccountDto(65L,890.25, 10L);

        accountList.add(accountOne);
        accountList.add(accountTwo);

    }

    @Test
    public void should_transform_dto_to_entity () {
        Account account = AccountMapper.INSTANCE.dtoToEntity(accountDto);
        Assertions.assertThat(account.getId()).isEqualTo(accountDto.getId());
        Assertions.assertThat(account.getBalance()).isEqualTo(accountDto.getBalance());

    }

    @Test
    public void should_transform_null_dto_to_null_entity () {
        Account account = AccountMapper.INSTANCE.dtoToEntity(null);
        Assertions.assertThat(account).isNull();

    }

    @Test
    public void should_transform_entity_to_dto () {
        AccountDto accountDto = AccountMapper.INSTANCE.entityToDto(accountTwo);
        Assertions.assertThat(accountDto.getId()).isEqualTo(accountTwo.getId());
        Assertions.assertThat(accountDto.getIdClient()).isEqualTo(accountTwo.getClient().getId());
        Assertions.assertThat(accountDto.getBalance()).isEqualTo(accountTwo.getBalance());
    }

    @Test
    public void should_transform_null_entity_to_null_dto () {
        AccountDto accountDto = AccountMapper.INSTANCE.entityToDto(null);
        Assertions.assertThat(accountDto).isNull();

    }

}
