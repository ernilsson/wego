package com.ernilsson.wego.domain.service;

import com.ernilsson.wego.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.security.Principal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = UserService.class)
public class UserServiceTest {
    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    public void findOrCreate_givenNonExistentUser_registersNewUser() {
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("Mocked");
        when(userRepository.findById(anyString())).thenReturn(Optional.empty());

        userService.findOrCreate(principal);

        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void findOrCreate_whenUserCreationFails_throwsException() {
        Principal principal = mock(Principal.class);
        when(principal.getName())
                .thenReturn("Mocked")
                .thenReturn(null);
        when(userRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrowsExactly(ServiceException.class, () -> {
            userService.findOrCreate(principal);
        });
    }
}
