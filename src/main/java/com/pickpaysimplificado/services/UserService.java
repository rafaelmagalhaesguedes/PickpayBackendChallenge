package com.pickpaysimplificado.services;

import com.pickpaysimplificado.entities.User;
import com.pickpaysimplificado.exceptions.NotFoundException;
import com.pickpaysimplificado.repositories.UserRepository;
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

    public User createUser(User user) throws NotFoundException {
        User userExists = userRepository.findUserByEmail(user.getEmail());

        if (userExists != null) throw new NotFoundException();

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
