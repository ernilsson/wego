package com.ernilsson.wego.domain;

import com.ernilsson.wego.domain.exceptions.InvalidUserStateException;
import lombok.Data;

@Data
public class User {
    private String id;

    public void register(String id) throws InvalidUserStateException {
        if (id == null || "".equals(id)) {
            throw new InvalidUserStateException();
        }
        this.id = id;
    }
}
