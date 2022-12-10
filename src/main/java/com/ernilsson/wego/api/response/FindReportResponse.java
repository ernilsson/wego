package com.ernilsson.wego.api.response;

import com.ernilsson.wego.domain.Report;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class FindReportResponse {
    private UUID id;
    private String note;
    private double latitude;
    private double longitude;
    private LocalDateTime createdAt;

    public static FindReportResponse from(Report report) {
        FindReportResponse response = new FindReportResponse();
        response.id = report.getId();
        response.note = report.getNote();
        response.latitude = report.getLatitude();
        response.longitude = report.getLongitude();
        response.createdAt = report.getCreatedAt();
        return response;
    }
}
