package com.pickpaysimplificado.dto;

import com.pickpaysimplificado.entities.Transaction;

public record ResponseDTO(String message, Transaction transaction) {
}
