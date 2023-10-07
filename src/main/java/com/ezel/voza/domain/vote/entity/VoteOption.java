package com.ezel.voza.domain.vote.entity;

import com.ezel.voza.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VoteOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id")
    private Vote vote;

    @Column(nullable = false)
    private String option;

    @ColumnDefault("0")
    private int count;

    @ElementCollection
    @CollectionTable(name = "voted_user", joinColumns = @JoinColumn(name = "option_id"))
    @MapKeyJoinColumn(name = "user_id")
    @Column(name = "user")
    @Builder.Default
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Map<User, String> users = new HashMap<>();

    public void addCount() {
        this.count++;
    }
}
