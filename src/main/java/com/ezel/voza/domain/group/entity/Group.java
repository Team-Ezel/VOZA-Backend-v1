package com.ezel.voza.domain.group.entity;

import com.ezel.voza.domain.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "meeting", indexes = {
        @Index(name = "idx_group_region", columnList = "region")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "group_id")
    private UUID uuid;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "leader_name")
    private String leaderName;

    @Column(name = "intro")
    private String introduceGroup;

    private String region;

    @ElementCollection
    @CollectionTable(name = "group_tags", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "tag")
    @Size(max = 3)
    @NotEmpty
    private Set<String> tags = new HashSet<>();

    @ElementCollection
    @CollectionTable(name="group_member", joinColumns = @JoinColumn(name= "group_id"))
    @Column(name = "users")
    @Size(max = 30)
    @NotEmpty
    private HashMap<User, String> members = new HashMap<>();

    public void putMember(User user, String role) {
        members.put(user, role);
    }
}
