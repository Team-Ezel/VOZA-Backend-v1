package com.ezel.voza.domain.vote.repository;

import com.ezel.voza.domain.vote.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}
