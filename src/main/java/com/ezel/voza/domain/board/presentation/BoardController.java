package com.ezel.voza.domain.board.presentation;

import com.ezel.voza.domain.board.presentation.dto.request.CreateBoardRequest;
import com.ezel.voza.domain.board.service.impl.CreateBoardServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping(value = "/board")
public class BoardController {

    private final CreateBoardServiceImpl createBoardService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateBoardRequest createBoardRequest) {
        createBoardService.execute(createBoardRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
