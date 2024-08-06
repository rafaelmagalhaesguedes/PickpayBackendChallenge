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

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Transactional
    public User createUser(User user) throws ConflictException {
        var documentExists = repository.findUserByDocument(user.getDocument());
        if (documentExists != null)
            throw new ConflictException("User with this document already exists.");

        var emailExists = repository.findUserByEmail(user.getEmail());
        if (emailExists != null)
            throw new ConflictException("User email already exists.");

        return repository.save(user);
    }

    public User findUserById(UUID id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public List<User> findAllUsers() {
        return repository.findAll();
    }
}
