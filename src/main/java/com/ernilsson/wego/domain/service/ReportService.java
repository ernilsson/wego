package com.ernilsson.wego.domain.service;

import com.ernilsson.wego.domain.Report;
import com.ernilsson.wego.domain.User;
import com.ernilsson.wego.domain.exceptions.InvalidReportException;
import com.ernilsson.wego.domain.repository.ReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ReportService {
    private final ReportRepository repository;

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }

    public UUID createReport(User publisher, Report report) throws InvalidReportException {
        try {
            report.publish(publisher);
            Optional<Report> result = repository.save(report);
            if (result.isEmpty()) {
                throw new ServiceException("Failed to create report resource");
            }
            return result.get().getId();
        } catch (InvalidReportException e) {
            log.warn("Attempted to publish invalid report: {}", report);
            throw e;
        }
    }

    public List<Report> findReportsInArea(double latitude, double longitude, int radius) {
        return repository.findInArea(latitude, longitude, radius);
    }

    public Optional<Report> findById(UUID id) {
        if (Objects.isNull(id)) {
            throw new ServiceException("Cannot find reports by null id");
        }
        return repository.findById(id);
    }
}
