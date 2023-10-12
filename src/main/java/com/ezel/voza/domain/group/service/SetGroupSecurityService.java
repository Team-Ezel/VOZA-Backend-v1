package com.ezel.voza.domain.group.service;

public interface SetGroupSecurityService {

    void execute(Boolean canEnter, Long groupId);
}
