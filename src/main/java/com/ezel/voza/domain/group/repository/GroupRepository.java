package com.ezel.voza.domain.group.repository;

import com.ezel.voza.domain.group.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {
}
