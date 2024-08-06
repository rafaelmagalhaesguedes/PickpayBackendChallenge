package com.pickpaysimplificado.config;

import com.pickpaysimplificado.entities.User;
import com.pickpaysimplificado.enums.UserType;
import com.pickpaysimplificado.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class SeederConfig {

    private final UserRepository userRepository;

    @Autowired
    public SeederConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    @Transactional
    public void seedDatabase() {
        seedUsers();
    }

    private void seedUsers() {
        List<User> users = Arrays.asList(
                new User(UUID.randomUUID(), "User", "Sender", "12000000000", "test01@email.com", "password", new BigDecimal("10.00"), UserType.COMMON),
                new User(UUID.randomUUID(), "User", "Receiver", "12300000000", "test02@email.com", "password", new BigDecimal("10.00"), UserType.MERCHANT)
           );
        userRepository.saveAll(users);
    }
}

