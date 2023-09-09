package com.ezel.voza.domain.vote.presentation;

import com.ezel.voza.domain.vote.presentation.dto.request.CreateVoteRequest;
import com.ezel.voza.domain.vote.presentation.dto.response.ListVoteResponse;
import com.ezel.voza.domain.vote.service.CreateVoteService;
import com.ezel.voza.domain.vote.service.DeleteVoteService;
import com.ezel.voza.domain.vote.service.ListVoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("group/{group_id}/vote")
public class VoteController {

    private final CreateVoteService createVoteService;

    private final ListVoteService listVoteService;

    private final DeleteVoteService deleteVoteService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateVoteRequest createVoteRequest, @PathVariable Long group_id) {
        createVoteService.execute(createVoteRequest, group_id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListVoteResponse> findAll(@PathVariable UUID group_id) {
        var list = listVoteService.execute(group_id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteVoteService.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
