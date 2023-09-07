package com.ezel.voza.domain.group.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@RedisHash("groupInvite")
public class GroupInvite {

    @Id
    private String email;

    private String inviteCode;

    private UUID groupId;

    @TimeToLive
    private Long expiresIn;
}
