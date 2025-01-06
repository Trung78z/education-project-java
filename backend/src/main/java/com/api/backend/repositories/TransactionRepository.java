package com.api.backend.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.backend.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @SuppressWarnings("null")
    Optional<Transaction> findById(Long id);

    Transaction deleteByUser_id(UUID userID);
}
