package com.ezel.voza.domain.report.service.impl;

import com.ezel.voza.domain.report.entity.Report;
import com.ezel.voza.domain.report.exception.FailedEmailSendException;
import com.ezel.voza.domain.report.exception.NotFoundReportException;
import com.ezel.voza.domain.report.presentation.dto.request.SendRefuseRequest;
import com.ezel.voza.domain.report.repository.ReportRepository;
import com.ezel.voza.domain.report.service.RefuseReportService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefuseReportServiceImpl implements RefuseReportService {

    private final ReportRepository reportRepository;
    private final JavaMailSender javaMailSender;

    @Override
    public void execute(Long reportId, SendRefuseRequest sendRefuseRequest) {

        Report report = reportRepository.findById(reportId)
                .orElseThrow(NotFoundReportException::new);

        String email = report.getUser().getEmail();

        sendRefuse(email, report.getTitle(), sendRefuseRequest);

        reportRepository.delete(report);
    }

    private void sendRefuse(String email, String title, SendRefuseRequest sendRefuseRequest) {

        String facade = "신고에 대한 답변이 도착했습니다";

        String content = "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div style=\"margin: 100px\">\n" +
                "        <h1>\n" +
                "            안녕하세요\n" +
                "            <p style=\"color: #007eff; margin: 0; padding: 0; display: inline\">\n" +
                "                Voza 개발팀\n" +
                "            </p>\n" +
                "            입니다!\n" +
                "        </h1>\n" +
                "        <br />\n" +
                "        <h2><p>Voza를 이용해주셔서 감사합니다. 귀하가 신청하신</p></h2>\n" + title + "\n" +
                "        <h2>\n" +
                "            <p>\n" +
                "                에 대한 신고가 거부 되었습니다.\n" +
                "            </p>\n" +
                "        </h2>\n" +
                "        <h2><p>거부 사유는 " + sendRefuseRequest.getMessage() + " 다음과 같습니다.\n" +
                "        <br />\n" +
                "        <p>\n" +
                "            왜 거부됐는지나, 추가로 궁금한 사항이 있다면 디스코드 배선후#0056으로 문의주세요.\n" +
                "        </p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            helper.setTo(email);
            helper.setSubject(facade);
            helper.setText(content, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new FailedEmailSendException();
        }
    }
}
