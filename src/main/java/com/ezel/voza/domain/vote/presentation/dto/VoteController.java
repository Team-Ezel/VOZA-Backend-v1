package com.ezel.voza.domain.vote.presentation.dto;

import com.ezel.voza.domain.vote.presentation.dto.request.CreateVoteRequest;
import com.ezel.voza.domain.vote.service.CreateVoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/vote")
public class VoteController {

    private final CreateVoteService createVoteService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateVoteRequest createVoteRequest) {
        createVoteService.execute(createVoteRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
