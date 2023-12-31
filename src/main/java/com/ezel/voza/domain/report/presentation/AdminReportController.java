package com.ezel.voza.domain.report.presentation;

import com.ezel.voza.domain.report.presentation.dto.request.SendRefuseRequest;
import com.ezel.voza.domain.report.presentation.dto.response.DetailReportResponse;
import com.ezel.voza.domain.report.presentation.dto.response.ListReportResponse;
import com.ezel.voza.domain.report.service.DetailReportService;
import com.ezel.voza.domain.report.service.ListReportService;
import com.ezel.voza.domain.report.service.RefuseReportService;
import com.ezel.voza.domain.report.service.RespondReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/report")
@RequiredArgsConstructor
public class AdminReportController {

    private final DetailReportService detailReportService;
    private final RespondReportService respondReportService;
    private final ListReportService listReportService;
    private final RefuseReportService refuseReportService;

    @GetMapping("/{reportId}")
    public ResponseEntity<DetailReportResponse> detailReport(@PathVariable Long reportId) {
        DetailReportResponse response = detailReportService.execute(reportId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{reportId}/approve")
    public ResponseEntity<Void> sendRespond(@PathVariable Long reportId) {
        respondReportService.execute(reportId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<ListReportResponse> getReportList() {
        var list = listReportService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/{reportId}/refuse")
    public ResponseEntity<Void> sendRefuse(
            @PathVariable Long reportId,
            @RequestBody @Valid SendRefuseRequest sendRefuseRequest
    ) {
        refuseReportService.execute(reportId, sendRefuseRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
