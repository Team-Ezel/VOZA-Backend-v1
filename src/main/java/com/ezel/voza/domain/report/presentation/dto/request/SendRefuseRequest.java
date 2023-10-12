package com.ezel.voza.domain.report.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SendRefuseRequest {

    @NotBlank(message = "거절 사유는 필수 입력값입니다.")
    private String message;
}
