package com.pickpaysimplificado.dto;

import com.pickpaysimplificado.entities.User;
import com.pickpaysimplificado.enums.UserType;

import java.math.BigDecimal;
import java.util.UUID;

public record UserDTO(
        UUID id,
        String firstName,
        String lastName,
        String document,
        BigDecimal balance,
        String email,
        UserType userType
) {
    public static UserDTO fromEntity(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDocument(),
                user.getBalance(),
                user.getEmail(),
                user.getUserType()
        );
    }
}
