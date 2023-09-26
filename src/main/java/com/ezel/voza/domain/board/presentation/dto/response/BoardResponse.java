package com.ezel.voza.domain.board.presentation.dto.response;

import com.ezel.voza.domain.board.entity.Board;
import com.ezel.voza.domain.board.entity.enums.BoardType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class BoardResponse {

    private Long id;

    private String title;

    private String author;

    private BoardType boardType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime createdDate;

    public static BoardResponse toResponse(Board board) {

        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .author(board.getAuthor())
                .boardType(board.getBoardType())
                .createdDate(board.getCreatedDate())
                .build();
    }
}
