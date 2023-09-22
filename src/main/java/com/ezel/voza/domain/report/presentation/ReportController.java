package com.ezel.voza.domain.report.presentation;

import com.ezel.voza.domain.report.presentation.dto.request.CreateReportRequest;
import com.ezel.voza.domain.report.service.SendReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {

    private final SendReportService sendReportService;

    @PostMapping
    public ResponseEntity<Void> createReport(@RequestBody @Valid CreateReportRequest createReportRequest) {
        sendReportService.execute(createReportRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
