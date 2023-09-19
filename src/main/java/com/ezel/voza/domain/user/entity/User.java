package com.ezel.voza.domain.user.entity;

import com.ezel.voza.domain.user.entity.enums.Banned;
import com.ezel.voza.domain.user.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = true, unique = true)
    private String email;

    @Column(name = "profileUrl")
    private String profileUrl;

    @Column(name = "name")
    private String nickName;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "ban")
    @Enumerated(EnumType.STRING)
    private Banned banned;

    public void updateRole(Role role) {
        this.role = role;
    }
}
