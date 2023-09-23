package com.ezel.voza.domain.calender.entity;

import com.ezel.voza.domain.calender.presentation.dto.request.UpdateCalenderRequest;
import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Calender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "calender_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    public void update(UpdateCalenderRequest updateCalenderRequest) {
        this.title = updateCalenderRequest.getTitle();
        this.content = updateCalenderRequest.getContent();
        this.startDate = updateCalenderRequest.getStartDate();
        this.endDate = updateCalenderRequest.getEndDate();
    }
}
