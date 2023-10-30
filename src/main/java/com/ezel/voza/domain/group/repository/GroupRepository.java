package com.ezel.voza.domain.group.repository;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("SELECT g FROM Group g INNER JOIN g.members m WHERE KEY(m) = :user")
    List<Group> findGroupsByMember(@Param("user") User user);

    List<Long> findGroupIdsByMembersContains(User currentUser);
}
