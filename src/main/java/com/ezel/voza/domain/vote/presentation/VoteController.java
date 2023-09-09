package com.ezel.voza.domain.vote.presentation;

import com.ezel.voza.domain.vote.presentation.dto.request.CreateVoteRequest;
import com.ezel.voza.domain.vote.presentation.dto.response.DetailVoteResponse;
import com.ezel.voza.domain.vote.presentation.dto.response.ListVoteResponse;
import com.ezel.voza.domain.vote.service.CreateVoteService;
import com.ezel.voza.domain.vote.service.DeleteVoteService;
import com.ezel.voza.domain.vote.service.GetVoteDetailService;
import com.ezel.voza.domain.vote.service.ListVoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("group/{group_id}/vote")
public class VoteController {

    private final CreateVoteService createVoteService;

    private final ListVoteService listVoteService;

    private final DeleteVoteService deleteVoteService;

    private final GetVoteDetailService getVoteDetailService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateVoteRequest createVoteRequest, @PathVariable Long group_id) {
        createVoteService.execute(createVoteRequest, group_id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListVoteResponse> findAll(@PathVariable Long group_id) {
        var list = listVoteService.execute(group_id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/{vote_id}")
    public ResponseEntity<Void> delete(@PathVariable Long vote_id) {
        deleteVoteService.execute(vote_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/{vote_id}")
    public ResponseEntity<DetailVoteResponse> findDetailOne(@PathVariable Long vote_id) {
        DetailVoteResponse oneFindById = getVoteDetailService.execute(vote_id);
        return new ResponseEntity<>(oneFindById, HttpStatus.OK);
    }

}
