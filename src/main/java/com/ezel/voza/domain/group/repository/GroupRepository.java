package com.ezel.voza.domain.group.repository;

import com.ezel.voza.domain.group.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
