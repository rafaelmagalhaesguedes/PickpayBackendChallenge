package com.pickpaysimplificado.dto;

import com.pickpaysimplificado.entities.User;
import com.pickpaysimplificado.enums.UserType;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.util.UUID;

public record UserCreationDTO(
        @NotBlank(message = "First name is required.")
        @Size(max = 255)
        String firstName,

        @NotBlank(message = "Last name is required.")
        @Size(max = 255)
        String lastName,

        @NotBlank(message = "Document is required.")
        @CPF(message = "CPF should be valid.")
        String document,

        @NotBlank(message = "Email is required.")
        @Email(message = "Email should be valid.")
        String email,

        @NotBlank(message = "Password is required.")
        String password,

        @NotNull(message = "Balance is required.")
        @PositiveOrZero(message = "Balance must be zero or positive.")
        BigDecimal balance,

        @NotNull(message = "User type is required.")
        UserType userType
) {
    public User toEntity() {
        return new User(firstName, lastName, document, email, password, balance, userType);
    }
}
