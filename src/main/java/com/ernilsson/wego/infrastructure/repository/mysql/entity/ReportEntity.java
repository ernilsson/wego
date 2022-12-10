package com.ernilsson.wego.infrastructure.repository.mysql.entity;

import com.ernilsson.wego.domain.Report;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "report")
@Data
public class ReportEntity {
    @Id
    private UUID id;
    private String note;
    private double latitude;
    private double longitude;
    private LocalDateTime createdAt;

    @ManyToOne
    private UserEntity publisher;

    public static ReportEntity from(Report report) {
        ReportEntity entity = new ReportEntity();
        entity.setId(report.getId());
        entity.setNote(report.getNote());
        entity.setLatitude(report.getLatitude());
        entity.setLongitude(report.getLongitude());
        entity.setCreatedAt(report.getCreatedAt());
        entity.setPublisher(UserEntity.from(report.getPublisher()));
        return entity;
    }

    public Report toReport() {
        Report report = new Report();
        report.setId(id);
        report.setNote(note);
        report.setLatitude(latitude);
        report.setLongitude(longitude);
        report.setCreatedAt(createdAt);
        return report;
    }
}
