package com.pickpaysimplificado.unit;

import static com.pickpaysimplificado.mocks.UserMock.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.pickpaysimplificado.entities.User;
import com.pickpaysimplificado.exceptions.ConflictException;
import com.pickpaysimplificado.exceptions.NotFoundException;
import com.pickpaysimplificado.repositories.UserRepository;
import com.pickpaysimplificado.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit Tests to User Service Class
 * *
 * Created by Rafa Guedes
 * */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService service;

    @Mock
    UserRepository repository;

    @Test
    public void testUserRetrievalById() throws NotFoundException {
        // Arrange
        var ID = USER.getId();

        when(repository.findById(eq(ID))).thenReturn(Optional.of(USER));

        // Act
        User userFromDb = service.findUserById(ID);
        verify(repository).findById(ID);

        // Assert
        assertThat(userFromDb).isEqualTo(USER);
    }

    @Test
    public void testUserRetrievalByDocument() {
        // Arrange
        var DOCUMENT = USER.getDocument();

        when(repository.findUserByDocument(eq(DOCUMENT))).thenReturn(USER);

        // Act
        User personFromDb = repository.findUserByDocument(DOCUMENT);
        verify(repository).findUserByDocument(DOCUMENT);

        // Assert
        assertThat(personFromDb).isEqualTo(USER);
    }

    @Test
    public void testUserRetrievalByEmail() {
        // Arrange
        var EMAIL = USER.getEmail();

        when(repository.findUserByEmail(eq(EMAIL))).thenReturn(USER);

        // Act
        User personFromDb = repository.findUserByEmail(EMAIL);
        verify(repository).findUserByEmail(EMAIL);

        // Assert
        assertThat(personFromDb).isEqualTo(USER);
    }

    @Test
    public void testCreateUser() throws ConflictException {
        // Arrange
        when(repository.save(USER_CREATION)).thenReturn(USER_CREATION);

        // Act
        var newPerson = service.createUser(USER_CREATION);

        // Assert
        assertThat(newPerson).isEqualTo(USER_CREATION);
    }
}
