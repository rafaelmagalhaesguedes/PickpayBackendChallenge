package com.pickpaysimplificado.exceptions;

public class NotFoundException extends Exception {
    public NotFoundException() {
        super("User not found.");
    }
}

