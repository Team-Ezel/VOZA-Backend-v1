package com.ezel.voza.domain.board.repository;

import com.ezel.voza.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
