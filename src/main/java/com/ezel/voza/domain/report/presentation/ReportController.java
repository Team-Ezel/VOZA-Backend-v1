package com.ezel.voza.domain.report.presentation;

import com.ezel.voza.domain.report.presentation.dto.request.CreateReportRequest;
import com.ezel.voza.domain.report.presentation.dto.response.DetailReportResponse;
import com.ezel.voza.domain.report.service.DetailReportService;
import com.ezel.voza.domain.report.service.SendReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final SendReportService sendReportService;
    private final DetailReportService detailReportService;

    @PostMapping
    public ResponseEntity<Void> createReport(@RequestBody @Valid CreateReportRequest createReportRequest) {
        sendReportService.execute(createReportRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<DetailReportResponse> detailReport(@PathVariable Long reportId) {
        DetailReportResponse response = detailReportService.execute(reportId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
