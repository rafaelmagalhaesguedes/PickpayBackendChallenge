package com.pickpaysimplificado.dto;

import com.pickpaysimplificado.entities.User;
import com.pickpaysimplificado.enums.UserType;

import java.math.BigDecimal;
import java.util.UUID;

public record UserCreationDTO(
        String firstName,
        String lastName,
        String document,
        String email,
        String password,
        BigDecimal balance,
        UserType userType

) {
    public User toEntity() {
        return new User(firstName, lastName, document, email, password, balance, userType);
    }
}
