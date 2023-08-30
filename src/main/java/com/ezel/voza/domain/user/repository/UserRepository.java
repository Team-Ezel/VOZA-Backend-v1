package com.ezel.voza.domain.user.repository;

import com.ezel.voza.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
