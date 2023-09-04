package com.ezel.voza.domain.board.entity;

import com.ezel.voza.domain.board.entity.enums.BoardType;
import com.ezel.voza.domain.board.presentation.dto.request.UpdateBoardRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    private String author;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime editedDate;

    public void update(UpdateBoardRequest updateBoardRequest) {
        this.title = updateBoardRequest.getTitle();
        this.content = updateBoardRequest.getContent();
    }
}