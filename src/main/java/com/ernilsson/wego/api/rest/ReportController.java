package com.ernilsson.wego.api.rest;

import com.ernilsson.wego.api.request.PublishReportRequest;
import com.ernilsson.wego.api.response.FindReportResponse;
import com.ernilsson.wego.api.response.FindReportsResponse;
import com.ernilsson.wego.api.response.PublishReportResponse;
import com.ernilsson.wego.domain.Report;
import com.ernilsson.wego.domain.User;
import com.ernilsson.wego.domain.exceptions.InvalidReportException;
import com.ernilsson.wego.domain.service.ReportService;
import com.ernilsson.wego.domain.service.UserService;
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
    private final UserService users;

    @Autowired
    public ReportController(ReportService reports, UserService users) {
        this.reports = reports;
        this.users = users;
    }

    @PostMapping
    public ResponseEntity<PublishReportResponse> publish(
            Principal principal, @RequestBody PublishReportRequest request) {
        try {
            User publisher = users.findOrCreate(principal);
            UUID id = reports.createReport(publisher, request.toReport());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new PublishReportResponse(id));
        } catch (InvalidReportException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid report data");
        }
    }

    @GetMapping
    public ResponseEntity<FindReportsResponse> findByArea(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("radius") int radius
    ) {
        final List<Report> reports = this.reports.findReportsInArea(latitude, longitude, radius);
        return ResponseEntity.ok(FindReportsResponse.from(reports));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindReportResponse> findById(@PathVariable UUID id) {
        Optional<Report> report = reports.findById(id);
        if (report.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found");
        }
        return ResponseEntity.ok(FindReportResponse.from(report.get()));
    }
}
