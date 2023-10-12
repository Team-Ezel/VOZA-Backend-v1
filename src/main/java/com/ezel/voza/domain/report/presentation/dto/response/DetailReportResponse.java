package com.ezel.voza.domain.report.presentation.dto.response;

import com.ezel.voza.domain.report.entity.enums.ReportType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailReportResponse {

    private String title;

    private String content;

    private String name;

    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    @CreatedDate
    private LocalDateTime createTime;
}
