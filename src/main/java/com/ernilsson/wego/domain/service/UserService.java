package com.ernilsson.wego.domain.service;

import com.ernilsson.wego.domain.User;
import com.ernilsson.wego.domain.exceptions.InvalidUserStateException;
import com.ernilsson.wego.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@Slf4j
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findOrCreate(Principal principal) {
        return repository.findById(principal.getName())
                .orElse(register(principal.getName()));
    }

    private User register(String id) {
        try {
            User user = new User();
            user.register(id);
            return repository.save(user);
        } catch (InvalidUserStateException e) {
            log.error("Failed to create user: ", e);
            throw new ServiceException(e);
        }
    }
}
