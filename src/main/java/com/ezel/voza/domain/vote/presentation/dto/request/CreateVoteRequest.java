package com.ezel.voza.domain.vote.presentation.dto.request;

import com.ezel.voza.domain.vote.entity.VoteOption;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateVoteRequest {

    @NotBlank(message = "제목은 필수 요소입니다.")
    private String title;

    @NotEmpty(message = "투표항목은 최소 한개이상 입력해야합니다.")
    private List<VoteOption> options;
}
