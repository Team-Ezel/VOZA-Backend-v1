package com.ezel.voza.domain.member.service;

public interface KickOutMemberService {

    void execute(Long groupId, Long userId, String kickOutTime);
}
