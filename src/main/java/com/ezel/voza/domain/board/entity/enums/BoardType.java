package com.ezel.voza.domain.board.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum BoardType {
    NORMAL, NOTICE;

    @JsonCreator
    public static BoardType from(String s) {
        return BoardType.valueOf(s.toUpperCase());
    }
}
