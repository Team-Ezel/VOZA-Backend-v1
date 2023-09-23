package com.ezel.voza.domain.report.service.impl;

import com.ezel.voza.domain.report.entity.Report;
import com.ezel.voza.domain.report.entity.enums.ReportType;
import com.ezel.voza.domain.report.presentation.dto.request.CreateReportRequest;
import com.ezel.voza.domain.report.repository.ReportRepository;
import com.ezel.voza.domain.report.service.SendReportService;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.util.UserUtil;
import com.ezel.voza.global.webhook.util.BotUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SendReportServiceImpl implements SendReportService {

    private final UserUtil userUtil;
    private final ReportRepository reportRepository;
    private final BotUtil botUtil;

    @Override
    public void execute(CreateReportRequest createReportRequest) {

        User user = userUtil.currentUser();

        Report report = Report.builder()
                .title(createReportRequest.getTitle())
                .content(createReportRequest.getContent())
                .name(user.getNickName())
                .reportType(ReportType.from(createReportRequest.getInquiryType()))
                .user(user)
                .createTime(LocalDateTime.now())
                .build();

        reportRepository.save(report);

        sendDiscordMessage(createReportRequest);
    }

    private void sendDiscordMessage(CreateReportRequest createReportRequest) {

        String botMessage = botUtil.message(createReportRequest.getTitle(), createReportRequest.getInquiryType());

        botUtil.sendDiscordMessage(botMessage);
    }
}
