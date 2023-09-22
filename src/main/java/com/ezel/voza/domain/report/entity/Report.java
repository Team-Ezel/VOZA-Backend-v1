package com.ezel.voza.domain.report.entity;

import com.ezel.voza.domain.report.entity.enums.ReportType;
import com.ezel.voza.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    @CreatedDate
    private LocalDateTime createTime;
}
