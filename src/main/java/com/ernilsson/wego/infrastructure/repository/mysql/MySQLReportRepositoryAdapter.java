package com.ernilsson.wego.infrastructure.repository.mysql;

import com.ernilsson.wego.domain.Report;
import com.ernilsson.wego.domain.repository.ReportRepository;
import com.ernilsson.wego.infrastructure.repository.mysql.entity.ReportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class MySQLReportRepositoryAdapter implements ReportRepository {
    private final MySQLReportRepository repository;

    @Autowired
    public MySQLReportRepositoryAdapter(MySQLReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Report> save(Report report) {
        ReportEntity entity = ReportEntity.from(report);
        entity = repository.save(entity);
        return Optional.of(entity.toReport());
    }

    @Override
    public List<Report> findInArea(double latitude, double longitude, int radius) {
        return repository
                .findByDistanceToPoint(latitude, longitude, radius)
                .stream()
                .map(ReportEntity::toReport)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Report> findById(UUID id) {
        Optional<ReportEntity> entity = repository.findById(id);
        if (entity.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(entity.get().toReport());
    }
}
