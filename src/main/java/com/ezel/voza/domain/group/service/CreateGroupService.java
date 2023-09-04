package com.ezel.voza.domain.group.service;

import com.ezel.voza.domain.group.presentation.dto.request.CreateGroupRequest;

public interface CreateGroupService {

    void execute(CreateGroupRequest createGroupRequest);
}
