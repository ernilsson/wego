package com.ernilsson.wego.api.model;

import com.ernilsson.wego.domain.Report;

import java.util.UUID;

public class PublishReport {
    
    public record Request(String note, double latitude, double longitude) {
        public Report toReport() {
            Report report = new Report();
            report.setNote(note);
            report.setLatitude(latitude);
            report.setLongitude(longitude);
            return report;
        }
    }

    public record Response(UUID id) {

    }
}
