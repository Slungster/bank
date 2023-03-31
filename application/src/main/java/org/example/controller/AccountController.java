package org.example.controller;

import org.example.data.AccountDto;
import org.example.ports.api.AccountServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountServicePort accountServicePort;

    @PostMapping(value = "/add")
    public ResponseEntity<AccountDto> addClient(@RequestBody AccountDto accountDto) {

        ResponseEntity result;
        try {
            AccountDto createdAccount = accountServicePort.createAccount(accountDto);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdAccount.getId()).toUri();
            result = ResponseEntity.created(location).body(createdAccount);
        }
        catch (IllegalArgumentException ex) {
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        catch (NoSuchElementException nex) {
            result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(nex.getMessage());
        }
        return result;
    }

    @PutMapping(value = "/credit")
    public ResponseEntity<AccountDto> creditAccount (@RequestParam Long accountId, double amount) {

        ResponseEntity result;
        try {
            result = ResponseEntity.ok(accountServicePort.creditAccount(accountId, amount));
        } catch (IllegalArgumentException ex) {
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (NoSuchElementException nex) {
            result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(nex.getMessage());
        }
        return result;
    }


    @PutMapping(value = "/debit")
    public ResponseEntity<AccountDto> debitAccount (@RequestParam Long accountId, double amount) {
        ResponseEntity result;
        try {
            result = ResponseEntity.ok(accountServicePort.debitAccount(accountId, amount));
        } catch (IllegalArgumentException ex) {
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (NoSuchElementException nex) {
            result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(nex.getMessage());
        }
        return result;
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<Double> getAccountBalance(@PathVariable long id) {

        ResponseEntity result;
        try {
            result = ResponseEntity.ok(accountServicePort.getAccountBalance(id));
        } catch (NoSuchElementException nex) {
            result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(nex.getMessage());
        }
        return result;

    }


}
