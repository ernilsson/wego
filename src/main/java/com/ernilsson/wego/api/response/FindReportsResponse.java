package com.ernilsson.wego.api.response;

import com.ernilsson.wego.domain.Report;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class FindReportsResponse {
    private List<FindReportResponse> reports;

    public static FindReportsResponse from(List<Report> source) {
        FindReportsResponse response = new FindReportsResponse();
        response.reports = source.stream().map(FindReportResponse::from).collect(Collectors.toList());
        return response;
    }
}
