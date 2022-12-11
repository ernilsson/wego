package com.ernilsson.wego.api.model;

import com.ernilsson.wego.domain.Report;

import java.util.List;
import java.util.stream.Collectors;

public class FindReports {
    public record Response(List<FindReport.Response> reports) {
        public static Response from(List<Report> source) {
            return new Response(
                    source.stream().map(FindReport.Response::from).collect(Collectors.toList())
            );
        }
    }
}
