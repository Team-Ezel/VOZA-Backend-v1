package com.ezel.voza.domain.report.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ReportType {
    USER, GROUP;

    @JsonCreator
    public static ReportType from(String s) { return ReportType.valueOf(s.toUpperCase()); }
}
