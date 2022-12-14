package com.ernilsson.wego.domain;

import com.ernilsson.wego.domain.exceptions.InvalidReportException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {
    @Test
    public void publish_givenNullPublisher_throwsException() {
        Report report = new Report();
        assertThrowsExactly(InvalidReportException.class, () -> {
            report.publish(null);
        });
    }

    @Test
    public void publish_givenOutOfRangeLatitude_throwsException() {
        Report report = new Report();
        report.setLatitude(-91);
        report.setLongitude(0);
        assertThrowsExactly(InvalidReportException.class, () -> {
            report.publish(new User());
        });

        report.setLatitude(91);
        report.setLongitude(0);
        assertThrowsExactly(InvalidReportException.class, () -> {
            report.publish(new User());
        });
    }

    @Test
    public void publish_givenOutOfRangeLongitude_throwsException() {
        Report report = new Report();
        report.setLongitude(-181);
        report.setLatitude(0);
        assertThrowsExactly(InvalidReportException.class, () -> {
            report.publish(new User());
        });

        report.setLongitude(181);
        assertThrowsExactly(InvalidReportException.class, () -> {
            report.publish(new User());
        });
    }

    @Test
    public void publish_setsPublisher() throws InvalidReportException {
        Report report = new Report();
        report.setLongitude(0);
        report.setLatitude(0);
        User user = new User();

        report.publish(user);

        assertEquals(user, report.getPublisher());
    }

    @Test
    public void publish_setsCreatedAt() throws InvalidReportException {
        Report report = new Report();
        report.setLongitude(0);
        report.setLatitude(0);

        report.publish(new User());

        assertNotNull(report.getCreatedAt());
    }

    @Test
    public void publish_setsId() throws InvalidReportException {
        Report report = new Report();
        report.setLongitude(0);
        report.setLatitude(0);

        report.publish(new User());

        assertNotNull(report.getId());
    }
}
