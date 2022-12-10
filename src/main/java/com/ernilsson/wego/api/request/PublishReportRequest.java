package com.ernilsson.wego.api.request;

import com.ernilsson.wego.domain.Report;
import lombok.Value;

@Value
public class PublishReportRequest {
    String note;
    double latitude;
    double longitude;

    public Report toReport() {
        Report report = new Report();
        report.setNote(note);
        report.setLatitude(latitude);
        report.setLongitude(longitude);
        return report;
    }
}
