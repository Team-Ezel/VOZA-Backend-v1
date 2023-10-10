package com.ezel.voza.domain.board.repository;

import com.ezel.voza.domain.board.entity.Board;
import com.ezel.voza.domain.group.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findALlByGroup(Group group);

    Optional<Board> findFirstByOrderByIdDesc();
}
