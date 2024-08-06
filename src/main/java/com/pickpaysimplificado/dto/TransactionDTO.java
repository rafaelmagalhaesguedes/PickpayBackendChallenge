package com.pickpaysimplificado.dto;

import com.pickpaysimplificado.entities.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionDTO(
        UUID id,
        BigDecimal value,
        UserDTO sender,
        UserDTO receiver,
        LocalDateTime timestamp
) {
    public static TransactionDTO fromEntity(Transaction transaction) {
        return new TransactionDTO(
                transaction.getId(),
                transaction.getAmount(),
                UserDTO.fromEntity(transaction.getSender()),
                UserDTO.fromEntity(transaction.getReceiver()),
                transaction.getTimestamp()
        );
    }
}
