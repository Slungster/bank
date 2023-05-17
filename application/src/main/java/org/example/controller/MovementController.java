package org.example.controller;

import org.example.data.MovementDto;
import org.example.ports.api.MovementServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/movement")
public class MovementController {

    @Autowired
    private MovementServicePort movementServicePort;

    @GetMapping(value = "/get")
    public ResponseEntity<List<MovementDto>> getMovementByAccountId (Long accountId) {
        ResponseEntity result;
        try {
            result = ResponseEntity.ok(movementServicePort.getMovementByAccountId(accountId));
        }
        catch (IllegalArgumentException ex) {
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
        catch (NoSuchElementException nex) {
            result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(nex.getMessage());
        }
        return result;
    }
}
