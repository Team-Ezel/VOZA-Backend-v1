package com.ezel.voza.domain.member.repository;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.member.entity.BlackMember;
import com.ezel.voza.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackMemberRepository extends JpaRepository<BlackMember, String> {

    BlackMember findByGroupAndUser(Group group, User user);
}
