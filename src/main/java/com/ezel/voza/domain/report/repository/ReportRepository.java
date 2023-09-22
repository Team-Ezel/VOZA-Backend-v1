package com.ezel.voza.domain.report.repository;

import com.ezel.voza.domain.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
