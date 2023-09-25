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
    MISMATCH_VOTE_OPTION("존재하지 않는 항목입니다.", 403),
    ALREADY_VOTED_USER("이미 투표한 유저입니다.", 403),

    //USER
    USER_NOT_FOUND("사용자를 찾을 수 없습니다", 404),
    ROLE_NOT_EXIST("역할이 존재하지 않습니다.", 404),

    //MEMBER
    MEMBER_NOT_FOUND("회원을 찾을 수 없습니다", 404),
    NOT_MANAGER("관리자 권한이 없습니다.", 403),

    //GROUP
    GROUP_NOT_FOUND("그룹을 찾을 수 없습니다", 404),
    NOT_LEADER("그룹 리더가 없습니다.", 403),
    MISMATCH_INVITE_CODE("초대 코드가 올바르지 않습니다.", 403),
    ALREADY_EXIST_GROUP("이미 그룹에 속해있습니다.", 403),
    NOT_EXIST_GROUP("그 그룹에 속해있지않습니다.", 403),
    LEADER_NOT_OUT_GROUP("리더는 그룹을 탈퇴할수없습니다.", 403),
    NOT_ALLOWED_ENTER_BAN_GROUP("정지된 그룹에 들어갈 수 없습니다.", 403),
    EXIST_BAN_GROUP("이미 정지된 그룹입니다.", 401),
    YOU_NOT_LEADER("리더만 그룹 관리를 할 수 있습니다.", 403),
    ENTER_ONLY_CODE("초대코드로만 입장할 수 있는 그룹입니다.", 403),

    //S3
    AILED_UPLOAD_IMAGE("이미지 업로드에 실패했습니다", 500),
    FILE_UPLOAD_FAIL("파일 업로드에 실패했습니다.", 500),
    NOT_ALLOWED_FILE("허용되지 않은 파일 형식입니다.", 400),
    INVALID_FORMAT_FILE("잘못된 형식의 파일입니다.", 400),

    //ADMIN
    YOU_NOT_ADMIN("어드민 권한이 없습니다.", 403),

    //BAN
    YOU_BANNED("정지된 사용자입니다. 관리자에게 문의하세요", 403),
    NOT_EXIST_BLACK_USER("정지 목록에 없습니다.", 404),

    //REPORT
    REPORT_NOT_EXIST("신고 목록이 존재 하지 않습니다.", 404),

    //EMAIL
    SEND_EMAIL_FAILED("이메일 전송에 실패했습니다", 500);


    private final String message;
    private final int status;
}
