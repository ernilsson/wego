package com.ernilsson.wego.domain.service;

public class ServiceException extends RuntimeException {
    public ServiceException(final String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
