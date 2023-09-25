package com.ezel.voza.domain.group.service;

import com.ezel.voza.domain.group.presentation.dto.request.EnterGroupRequest;

public interface EnterByCodeGroupService {

    void execute(EnterGroupRequest enterGroupRequest);
}
