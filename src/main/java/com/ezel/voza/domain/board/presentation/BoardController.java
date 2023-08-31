package com.ezel.voza.domain.board.presentation;

import com.ezel.voza.domain.board.presentation.dto.request.CreateBoardRequest;
import com.ezel.voza.domain.board.presentation.dto.request.UpdateBoardRequest;
import com.ezel.voza.domain.board.presentation.dto.response.ListBoardResponse;
import com.ezel.voza.domain.board.service.CreateBoardService;
import com.ezel.voza.domain.board.service.ListBoardService;
import com.ezel.voza.domain.board.service.UpdateBoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final CreateBoardService createBoardService;

    private final ListBoardService listBoardService;

    private final UpdateBoardService updateBoardService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateBoardRequest createBoardRequest) {
        createBoardService.execute(createBoardRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListBoardResponse> findAll() {
        var list = listBoardService.execute();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid UpdateBoardRequest updateBoardRequest) {
        updateBoardService.execute(id, updateBoardRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
