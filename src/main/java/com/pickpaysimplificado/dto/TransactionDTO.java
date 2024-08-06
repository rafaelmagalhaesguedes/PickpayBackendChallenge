package com.pickpaysimplificado.dto;

import com.pickpaysimplificado.entities.Transaction;
import com.pickpaysimplificado.entities.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionDTO(
        UUID id,
        BigDecimal value,
        User sender,
        User receiver,
        LocalDateTime timestamp
) {
    public static TransactionDTO fromEntity(Transaction transaction) {
        return new TransactionDTO(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getSender(),
                transaction.getReceiver(),
                transaction.getTimestamp()
        );
    }
}
