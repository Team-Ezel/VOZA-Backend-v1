package com.ezel.voza.domain.chat.service;

import com.ezel.voza.domain.group.entity.Group;

public interface RoomOutService {

    void execute(Long groupId, Group group);
}
