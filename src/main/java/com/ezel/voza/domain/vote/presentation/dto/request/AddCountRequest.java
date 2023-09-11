package com.ezel.voza.domain.vote.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddCountRequest {

    @NotBlank(message = "Id는 필수 요소입니다.")
    private Long id;
}
