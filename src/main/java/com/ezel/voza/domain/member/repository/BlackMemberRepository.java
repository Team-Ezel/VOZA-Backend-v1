package com.ezel.voza.domain.member.repository;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.member.entity.BlackMember;
import com.ezel.voza.domain.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlackMemberRepository extends JpaRepository<BlackMember, String> {

    BlackMember findByGroupAndUser(Group group, User user);

    @EntityGraph(attributePaths = {"user", "group"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT DISTINCT bm FROM BlackMember bm LEFT JOIN bm.user LEFT JOIN bm.group")
    List<BlackMember> findAllEntityGraph();
}
