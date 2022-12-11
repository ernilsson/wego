package com.ernilsson.wego.api.model;

import com.ernilsson.wego.domain.Report;

import java.time.LocalDateTime;
import java.util.UUID;

public class FindReport {
    public record Response(UUID id, String note, double latitude,
                           double longitude, LocalDateTime createdAt) {
        public static Response from(Report report) {
            return new Response(
                    report.getId(),
                    report.getNote(),
                    report.getLatitude(),
                    report.getLongitude(),
                    report.getCreatedAt()
            );
        }
    }
}
