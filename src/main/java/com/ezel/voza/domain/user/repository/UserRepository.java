package com.ezel.voza.domain.user.repository;

import com.ezel.voza.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNickName(String nickName);

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
