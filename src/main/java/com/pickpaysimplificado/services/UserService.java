package com.pickpaysimplificado.services;

import com.pickpaysimplificado.entities.User;
import com.pickpaysimplificado.exceptions.ConflictException;
import com.pickpaysimplificado.exceptions.NotFoundException;
import com.pickpaysimplificado.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(User user) throws ConflictException {
        User userExists = userRepository.findUserByEmail(user.getEmail());
        if (userExists != null) {
            throw new ConflictException("User email already exists.");
        }

        User documentExists = userRepository.findUserByDocument(user.getDocument());
        if (documentExists != null) {
            throw new ConflictException("User with this document already exists.");
        }

        return userRepository.save(user);
    }

    public User findUserById(UUID id) throws NotFoundException {
        return userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
