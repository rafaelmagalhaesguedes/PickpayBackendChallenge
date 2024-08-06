package com.pickpaysimplificado.database;

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
public class UserSeeder {

    private final UserRepository userRepository;

    @Autowired
    public UserSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    @Transactional
    public void seedDatabase() {
        seedUsers();
    }

    private void seedUsers() {
        List<User> users = Arrays.asList(
                new User(UUID.randomUUID(), "Common", "Sender", "86637514004", "test01@email.com", "password", new BigDecimal("10.00"), UserType.COMMON),
                new User(UUID.randomUUID(), "Merchant", "Receiver", "04943133070", "test02@email.com", "password", new BigDecimal("10.00"), UserType.MERCHANT)
           );
        userRepository.saveAll(users);
    }
}

