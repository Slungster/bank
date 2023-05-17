package org.example.repositories;

import org.example.entities.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {

    List<Movement> findAllByAccountId (Long accountId);
}
