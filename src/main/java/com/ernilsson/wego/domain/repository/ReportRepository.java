package com.ernilsson.wego.domain.repository;

import com.ernilsson.wego.domain.Report;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReportRepository {
    Optional<Report> save(Report report);

    List<Report> findInArea(double latitude, double longitude, int radius);

    Optional<Report> findById(UUID id);
}
