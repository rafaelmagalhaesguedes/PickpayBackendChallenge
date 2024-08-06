package com.pickpaysimplificado.repositories;

import com.pickpaysimplificado.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findUserByDocument(String document);

    User findUserById(UUID id);

    User findUserByEmail(String email);
}
