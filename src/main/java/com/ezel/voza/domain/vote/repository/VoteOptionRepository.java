package com.ezel.voza.domain.vote.repository;

import com.ezel.voza.domain.vote.entity.VoteOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteOptionRepository extends JpaRepository<VoteOption, Long> {

    VoteOption findOneById(Long id);
}
