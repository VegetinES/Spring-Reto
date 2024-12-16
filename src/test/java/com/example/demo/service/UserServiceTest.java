package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryImpl();
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void whenGetUserById_thenUserIsReturned() {
        // Crear y guardar un usuario
        User user = new User(null, "Test User", "test@example.com");
        User savedUser = userRepository.save(user);

        // Buscar el usuario
        Optional<User> found = userService.getUserById(savedUser.getId());

        // Verificar
        assertTrue(found.isPresent());
        assertEquals("Test User", found.get().getName());
        assertEquals("test@example.com", found.get().getEmail());
    }
}