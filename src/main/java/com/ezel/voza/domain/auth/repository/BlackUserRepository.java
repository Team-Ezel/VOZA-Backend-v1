package com.ezel.voza.domain.auth.repository;

import com.ezel.voza.domain.auth.entity.BlackUser;
import com.ezel.voza.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackUserRepository extends JpaRepository<BlackUser, Long> {

    Boolean existsByUser (User user);
}
