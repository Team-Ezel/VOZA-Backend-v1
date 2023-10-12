package com.ezel.voza.domain.user.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Banned {
    BAN, UNBAN;

    @JsonCreator
    public static Banned from(String s) { return Banned.from(s.toUpperCase()); }
}
