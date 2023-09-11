package com.ezel.voza.domain.group.service.impl;

import com.ezel.voza.domain.file.service.SingleFileUploadService;
import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.group.presentation.dto.request.CreateGroupRequest;
import com.ezel.voza.domain.group.repository.GroupRepository;
import com.ezel.voza.domain.group.service.CreateGroupService;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class CreateGroupServiceImpl implements CreateGroupService {

    private final GroupRepository groupRepository;
    private final UserUtil util;
    private final SingleFileUploadService singleFileUploadService;

    @Override
    public void execute(CreateGroupRequest createGroupRequest, MultipartFile file) {

        String fileUrl = null;
        if (file != null) {
            fileUrl = singleFileUploadService.uploadFile(file);
        }

        User user = util.currentUser();

        Group group = Group.builder()
                .groupName(createGroupRequest.getGroupName())
                .leaderName(user.getNickName())
                .introduceGroup(createGroupRequest.getIntroduceGroup())
                .region(createGroupRequest.getRegion())
                .tags(new HashSet<>(createGroupRequest.getTags()))
                .members(new HashMap<>())
                .url(fileUrl)
                .build();

        group.putMember(user, "Leader");

        groupRepository.save(group);
    }
}
