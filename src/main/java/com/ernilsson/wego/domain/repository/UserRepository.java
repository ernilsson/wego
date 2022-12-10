package com.ernilsson.wego.domain.repository;

import com.ernilsson.wego.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(String id);

    User save(User user);
}
