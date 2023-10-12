package com.ezel.voza.domain.group.service;

import com.ezel.voza.domain.group.presentation.dto.request.CreateGroupRequest;
import org.springframework.web.multipart.MultipartFile;

public interface CreateGroupService {

    void execute(CreateGroupRequest createGroupRequest, MultipartFile file);
}
