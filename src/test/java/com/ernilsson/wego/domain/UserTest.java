package com.ernilsson.wego.domain;

import com.ernilsson.wego.domain.exceptions.InvalidUserStateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;


class UserTest {
    @Test
    public void register_givenEmptyId_throwsException() {
        User user = new User();
        assertThrowsExactly(InvalidUserStateException.class, () -> {
            user.register("");
        });
    }

    @Test
    public void register_givenNullId_throwsException() {
        User user = new User();
        assertThrowsExactly(InvalidUserStateException.class, () -> {
            user.register(null);
        });
    }
}
