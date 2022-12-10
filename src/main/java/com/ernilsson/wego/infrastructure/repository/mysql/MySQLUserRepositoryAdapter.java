package com.ernilsson.wego.infrastructure.repository.mysql;

import com.ernilsson.wego.domain.User;
import com.ernilsson.wego.domain.repository.UserRepository;
import com.ernilsson.wego.infrastructure.repository.mysql.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MySQLUserRepositoryAdapter implements UserRepository {
    private final MySQLUserRepository repository;

    @Autowired
    public MySQLUserRepositoryAdapter(MySQLUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> findById(String id) {
        Optional<UserEntity> entity = repository.findById(id);
        return entity.map(UserEntity::toUser);
    }

    @Override
    public User save(User user) {
        UserEntity entity = UserEntity.from(user);
        entity = repository.save(entity);
        return entity.toUser();
    }
}
