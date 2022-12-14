package com.ernilsson.wego.domain.service;

import com.ernilsson.wego.domain.Report;
import com.ernilsson.wego.domain.User;
import com.ernilsson.wego.domain.exceptions.InvalidReportException;
import com.ernilsson.wego.domain.repository.ReportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ReportService.class)
public class ReportServiceTest {
    @MockBean
    ReportRepository reportRepository;

    @MockBean
    UserService userService;

    @Autowired
    ReportService reportService;

    @Test
    public void createReport_givenFailedReportCreation_throwsException() {
        when(userService.findOrCreate(any())).thenReturn(new User());
        when(reportRepository.save(any())).thenReturn(Optional.empty());

        assertThrowsExactly(ServiceException.class, () ->
                reportService.createReport(mock(Principal.class), new Report()));
        verify(userService, times(1)).findOrCreate(any());
        verify(reportRepository, times(1)).save(any());
    }

    @Test
    public void createReport_givenInvalidReport_throwsException() {
        when(userService.findOrCreate(any())).thenReturn(new User());
        when(reportRepository.save(any())).thenReturn(Optional.empty());

        Report report = new Report();
        report.setLatitude(-91);

        assertThrowsExactly(ServiceException.class, () ->
                reportService.createReport(mock(Principal.class), report));
    }

    @Test
    public void createReport_returnsNewlyCreatedId() throws InvalidReportException {
        when(userService.findOrCreate(any())).thenReturn(new User());
        Report persisted = new Report();
        persisted.setId(UUID.randomUUID());
        when(reportRepository.save(any())).thenReturn(Optional.of(persisted));

        Report report = new Report();
        UUID id = reportService.createReport(mock(Principal.class), report);

        assertNotNull(id);
        assertEquals(persisted.getId(), id);
    }

    @Test
    public void findById_givenNullId_throwsException() {
        assertThrowsExactly(ServiceException.class, () -> {
            reportService.findById(null);
        });
    }

    @Test
    public void findById_returnsRepositoryResult() {
        Report persisted = new Report();
        persisted.setId(UUID.randomUUID());
        when(reportRepository.findById(any())).thenReturn(Optional.of(persisted));

        Optional<Report> found = reportService.findById(persisted.getId());

        assertTrue(found.isPresent());
        assertEquals(persisted, found.get());
    }
}
