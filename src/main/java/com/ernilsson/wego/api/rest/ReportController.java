package com.ernilsson.wego.api.rest;

import com.ernilsson.wego.api.model.FindReport;
import com.ernilsson.wego.api.model.FindReports;
import com.ernilsson.wego.api.model.PublishReport;
import com.ernilsson.wego.domain.Report;
import com.ernilsson.wego.domain.exceptions.InvalidReportException;
import com.ernilsson.wego.domain.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reports;

    @Autowired
    public ReportController(ReportService reports) {
        this.reports = reports;
    }

    @PostMapping
    public ResponseEntity<PublishReport.Response> publish(
            Principal principal, @RequestBody PublishReport.Request request) {
        try {
            UUID id = reports.createReport(principal, request.toReport());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new PublishReport.Response(id));
        } catch (InvalidReportException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid report data");
        }
    }

    @GetMapping
    public ResponseEntity<FindReports.Response> findByArea(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("radius") int radius
    ) {
        final List<Report> reports = this.reports.findReportsInArea(latitude, longitude, radius);
        return ResponseEntity.ok(FindReports.Response.from(reports));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindReport.Response> findById(@PathVariable UUID id) {
        Optional<Report> report = reports.findById(id);
        if (report.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found");
        }
        return ResponseEntity.ok(FindReport.Response.from(report.get()));
    }
}
