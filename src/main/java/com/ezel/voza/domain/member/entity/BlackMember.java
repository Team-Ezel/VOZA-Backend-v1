package com.ezel.voza.domain.member.entity;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.member.entity.enums.KickOutTime;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.util.KickOutTimeUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlackMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "black_member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "kick_out_time")
    private LocalDateTime kickOutTime;

    public void setKickOutTime(KickOutTime kickOutTime) {
        this.kickOutTime = LocalDateTime.now().plusSeconds(KickOutTimeUtil.getSeconds(kickOutTime));
    }
}
