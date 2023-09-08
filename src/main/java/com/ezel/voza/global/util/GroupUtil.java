package com.ezel.voza.global.util;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.group.exception.GroupNotFoundException;
import com.ezel.voza.domain.group.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GroupUtil {

    private final GroupRepository groupRepository;

    public Group findGroupById(UUID id) {

        return groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException());
    }
}
