package com.pet.pethaven.service;

import com.pet.pethaven.model.User;
import com.pet.pethaven.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Uruchamia wsparcie dla Mockito
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService; // Automatycznie wstrzykuje mocki do serwisu

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setPhoneNumber("123456789");
        user.setPassword("rawPassword123");
    }

    @Test
    void shouldSaveUserSuccessfully() {
        // GIVEN (Przygotowanie danych i zachowań)
        String encodedPass = "encodedSecret";
        when(passwordEncoder.encode("rawPassword123")).thenReturn(encodedPass);
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        // WHEN (Wykonanie testowanej metody)
        User savedUser = userService.saveUser(user);

        // THEN (Weryfikacja wyników)
        assertNotNull(savedUser);
        assertEquals(encodedPass, savedUser.getPassword());
        verify(passwordEncoder, times(1)).encode(anyString());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void shouldFailValidationWhenPhoneNumberIsInvalid() {
        // GIVEN
        user.setPhoneNumber("invalid");
        // Zakładamy, że validatePhoneNumber rzuca RuntimeException dla błędnych danych
        // Jeśli Twoja metoda rzuca specyficzny wyjątek (np. ValidationException), zmień go tutaj:

        // WHEN & THEN
        assertThrows(RuntimeException.class, () -> {
            userService.saveUser(user);
        });

        // Weryfikujemy, że repozytorium NIGDY nie zostało wywołane przez błąd walidacji
        verify(userRepository, never()).save(any());
    }
}