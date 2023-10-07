package com.ezel.voza.domain.vote.presentation;

import com.ezel.voza.domain.vote.presentation.dto.request.CreateVoteRequest;
import com.ezel.voza.domain.vote.presentation.dto.response.DetailVoteResponse;
import com.ezel.voza.domain.vote.presentation.dto.response.ListVoteResponse;
import com.ezel.voza.domain.vote.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("group/{groupId}/vote")
public class VoteController {

    private final CreateVoteService createVoteService;

    private final ListVoteService listVoteService;

    private final DeleteVoteService deleteVoteService;

    private final GetVoteDetailService getVoteDetailService;

    private final AddCountService addCountService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateVoteRequest createVoteRequest, @PathVariable Long groupId) {
        createVoteService.execute(createVoteRequest, groupId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListVoteResponse> findAll(@PathVariable Long groupId) {
        var list = listVoteService.execute(groupId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/{voteId}")
    public ResponseEntity<Void> delete(@PathVariable Long voteId) {
        deleteVoteService.execute(voteId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/{voteId}")
    public ResponseEntity<DetailVoteResponse> findDetailOne(@PathVariable Long voteId) {
        DetailVoteResponse oneFindById = getVoteDetailService.execute(voteId);
        return new ResponseEntity<>(oneFindById, HttpStatus.OK);
    }

    @PatchMapping("/{voteId}/{optionId}")
    public ResponseEntity<Void> addCount(@PathVariable Long voteId, @PathVariable Long optionId) {
        addCountService.execute(voteId, optionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
