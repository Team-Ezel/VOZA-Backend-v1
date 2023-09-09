package com.ezel.voza.domain.vote.repository;

import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.vote.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findAllByGroup(Group group);
}
