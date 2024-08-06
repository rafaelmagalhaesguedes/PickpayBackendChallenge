package com.pickpaysimplificado.repositories;

import com.pickpaysimplificado.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
