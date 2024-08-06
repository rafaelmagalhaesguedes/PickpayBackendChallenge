package com.pickpaysimplificado.controller;

import com.pickpaysimplificado.dto.UserCreationDTO;
import com.pickpaysimplificado.dto.UserDTO;
import com.pickpaysimplificado.entities.User;
import com.pickpaysimplificado.exceptions.NotFoundException;
import com.pickpaysimplificado.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserCreationDTO userCreationDTO) throws NotFoundException {
        return UserDTO.fromEntity(
                userService.createUser(userCreationDTO.toEntity())
        );
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable UUID id) throws NotFoundException {
        return UserDTO.fromEntity(
                userService.findUserById(id)
        );
    }

    @GetMapping()
    public List<UserDTO> getAllUsers() throws NotFoundException {
        List<User> userList = userService.findAllUsers();

        return userList.stream().map(UserDTO::fromEntity).toList();
    }
}
