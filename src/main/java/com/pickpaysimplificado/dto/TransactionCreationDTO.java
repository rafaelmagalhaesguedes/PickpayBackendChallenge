package com.pickpaysimplificado.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionCreationDTO(
        BigDecimal value,
        UUID senderId,
        UUID receiverId
) { }
