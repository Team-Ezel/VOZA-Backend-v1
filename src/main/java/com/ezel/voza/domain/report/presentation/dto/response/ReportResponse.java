package com.ezel.voza.domain.report.presentation.dto.response;

import com.ezel.voza.domain.report.entity.Report;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReportResponse {

    private Long reportId;

    private String title;

    private String content;

    private String email;

    public static ReportResponse toResponse(Report report) {

        return ReportResponse.builder()
                .reportId(report.getId())
                .title(report.getTitle())
                .content(report.getContent())
                .email(report.getUser().getEmail())
                .build();
    }
}
