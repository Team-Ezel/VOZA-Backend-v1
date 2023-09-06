package com.ezel.voza.domain.group.service;

import java.util.UUID;

public interface CreateGroupInviteCode {

    String generateInviteCode(UUID groupId);
}
