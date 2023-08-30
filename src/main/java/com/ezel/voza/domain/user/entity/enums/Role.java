package com.ezel.voza.domain.user.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
    ROLE_USER, ROLE_ADMIN;

    @JsonCreator
    public static Role from(String s) { return Role.valueOf(s.toUpperCase()); }
}
