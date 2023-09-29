package com.ezel.voza.domain.board.presentation;

import com.ezel.voza.domain.board.presentation.dto.request.CreateBoardRequest;
import com.ezel.voza.domain.board.presentation.dto.request.UpdateBoardRequest;
import com.ezel.voza.domain.board.presentation.dto.response.DetailBoardResponse;
import com.ezel.voza.domain.board.presentation.dto.response.ListBoardResponse;
import com.ezel.voza.domain.board.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group/{group_id}/board")
public class BoardController {

    private final CreateBoardService createBoardService;

    private final ListBoardService listBoardService;

    private final UpdateBoardService updateBoardService;

    private final GetBoardDetailService getBoardDetailService;

    private final DeleteBoardService deleteBoardService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateBoardRequest createBoardRequest, @PathVariable Long group_id) {
        createBoardService.execute(createBoardRequest, group_id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListBoardResponse> findAll(@PathVariable Long group_id) {
        var list = listBoardService.execute(group_id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{board_id}")
    public ResponseEntity<DetailBoardResponse> findDetailOne(@PathVariable Long board_id) {
        DetailBoardResponse oneFindById = getBoardDetailService.execute(board_id);
        return new ResponseEntity<>(oneFindById, HttpStatus.OK);
    }

    @PatchMapping("/{board_id}")
    public ResponseEntity<Void> update(@PathVariable Long board_id, @RequestBody @Valid UpdateBoardRequest updateBoardRequest) {
        updateBoardService.execute(board_id, updateBoardRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{board_id}")
    public ResponseEntity<Void> delete(@PathVariable Long board_id) {
        deleteBoardService.execute(board_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
