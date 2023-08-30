package com.ezel.voza.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //SERVER ERROR
    UNKNOWN_ERROR("알 수 없는 오류입니다.", 500),

    //TOKEN
    TOKEN_IS_EXPIRED("토큰이 만료 되었습니다.", 401),
    TOKEN_NOT_VALID("토큰이 유효 하지 않습니다.", 401),

    //BOARD
    BOARD_NOT_FOUND("게시글을 찾을 수 없습니다.", 404),
    MISMATCH_BOARD_AUTHOR("내가 작성한 글이 아닙니다.", 403),

    //CALENDER
    CALENDER_NOT_FOUND("일정을 찾지 못했습니다.", 404),
    MISMATCH_CALENDER_AUTHOR("내가 작성한 일정이 아닙니다.", 403),

    //VOTE
    VOTE_NOT_FOUND("투표를 찾지 못했습니다.", 404),
    MISMATCH_VOTE_AUTHOR("내가 작성한 투표가 아닙니다.", 403),

    //USER
    USER_NOT_FOUND("사용자를 찾을 수 없습니다", 404),
    ROLE_NOT_EXIST("역할이 존재하지 않습니다.", 404),

    //MEMBER
    MEMBER_NOT_FOUND("회원을 찾을 수 없습니다", 404),
    NOT_MANAGER("관리자 권한이 없습니다.", 403);

    private final String message;
    private final int status;
}
