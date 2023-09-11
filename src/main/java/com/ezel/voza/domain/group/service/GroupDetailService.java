package com.ezel.voza.domain.group.service;

import com.ezel.voza.domain.group.presentation.dto.response.GroupDetailResponse;

public interface GroupDetailService {

    GroupDetailResponse execute(Long groupId);
}
