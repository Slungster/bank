package org.example.controller;

import org.example.data.ClientDto;
import org.example.ports.api.ClientServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientServicePort clientServicePort;

    @PostMapping(value = "/add")
    public ResponseEntity<ClientDto> addClient(@RequestBody ClientDto clientDto) {

        ResponseEntity result;
        try {
            ClientDto createdClient = clientServicePort.createClient(clientDto);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdClient.getId()).toUri();
            result = ResponseEntity.created(location).body(createdClient);
        }
        catch (IllegalArgumentException ex) {
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        return result;
    }

    @PutMapping("/update")
    public ResponseEntity<ClientDto> updateClient(@RequestBody ClientDto clientDto) {

        ResponseEntity result;
        try {
            result = ResponseEntity.ok(clientServicePort.updateClient(clientDto));
        }
        catch (IllegalArgumentException ex) {
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        catch (NoSuchElementException nex) {
            result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(nex.getMessage());
        }
        return result;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable long id) {
        //return clientServicePort.getClientById(id);
        ResponseEntity result;
        try {
            result = ResponseEntity.ok(clientServicePort.getClientById(id));
        }
        catch (NoSuchElementException nex) {
            result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(nex.getMessage());
        }
        return result;
    }

    @GetMapping("/get")
    public List<ClientDto> getAllClients() {
        return clientServicePort.getClients();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteClientById(@PathVariable long id) {

        ResponseEntity result;
        try {
            clientServicePort.deleteClientById(id);
            result = ResponseEntity.ok("Client id " + id + " supprimé");
        }
        catch (NoSuchElementException nex) {
            result = ResponseEntity.badRequest().body(nex.getMessage());
        }
        return result;
    }
}
