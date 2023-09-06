package com.ezel.voza.domain.group.repository;

import com.ezel.voza.domain.group.entity.GroupInvite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupInviteRepository extends JpaRepository<GroupInvite, String> {
}
