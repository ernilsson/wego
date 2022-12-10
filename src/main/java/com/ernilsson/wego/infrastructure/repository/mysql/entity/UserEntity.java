package com.ernilsson.wego.infrastructure.repository.mysql.entity;

import com.ernilsson.wego.domain.User;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "user")
@Data
public class UserEntity {
    @Id
    private String id;

    public static UserEntity from(User user) {
        UserEntity entity = new UserEntity();
        entity.id = user.getId();
        return entity;
    }

    public User toUser() {
        User user = new User();
        user.setId(id);
        return user;
    }
}
