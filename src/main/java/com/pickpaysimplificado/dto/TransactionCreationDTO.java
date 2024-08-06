package com.pickpaysimplificado.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionCreationDTO(
        @NotNull(message = "The amount of transaction is required.")
        @Positive(message = "The amount of transaction must be positive.")
        BigDecimal value,

        @NotNull(message = "Sender ID is required.")
        UUID senderId,

        @NotNull(message = "Receiver ID is required.")
        UUID receiverId
) { }
