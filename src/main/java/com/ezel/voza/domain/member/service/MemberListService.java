package com.ezel.voza.domain.member.service;

import com.ezel.voza.domain.member.presentation.response.MemberListResponse;

public interface MemberListService {

    MemberListResponse execute(Long id);
}
