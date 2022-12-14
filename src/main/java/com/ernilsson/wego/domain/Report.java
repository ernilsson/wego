package com.ernilsson.wego.domain;

import com.ernilsson.wego.domain.exceptions.InvalidReportException;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Report {
    private UUID id;

    private String note;
    private double latitude;
    private double longitude;
    private User publisher;
    private LocalDateTime createdAt;

    public void publish(User user) throws InvalidReportException {
        if (user == null || isInvalid()) {
            throw new InvalidReportException();
        }
        this.publisher = user;
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

    private boolean isInvalid() {
        return hasInvalidLatitude() || hasInvalidLongitude();
    }

    private boolean hasInvalidLatitude() {
        return latitude < -90 || latitude > 90;
    }

    private boolean hasInvalidLongitude() {
        return longitude < -180 || longitude > 180;
    }
}
