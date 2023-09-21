package com.ezel.voza.domain.group.entity;

import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "meeting", indexes = {
        @Index(name = "idx_group_region", columnList = "region")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Group extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "leader_name")
    private String leaderName;

    @Column(name = "intro")
    private String introduceGroup;

    private String region;

    private Boolean stop;

    @ElementCollection
    @CollectionTable(name = "group_tags", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "tag")
    @Size(max = 3)
    @NotEmpty
    private Set<String> tags = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "group_member", joinColumns = @JoinColumn(name = "group_id"))
    @MapKeyJoinColumn(name = "user_id")
    @Column(name = "role")
    @NotEmpty
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 또는 다른 적절한 전략 선택
    private Map<User, String> members = new HashMap<>();

    @Column(name = "group_file_url")
    private String url;

    public void putMember(User user, String role) {
        members.put(user, role);
    }

    public void deleteMember(User user) { members.remove(user); }

    public void setBan(Boolean banType) {
        this.stop = banType;
    }
}
