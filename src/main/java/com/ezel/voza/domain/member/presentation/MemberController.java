package com.ezel.voza.domain.member.presentation;

import com.ezel.voza.domain.member.presentation.response.MemberListResponse;
import com.ezel.voza.domain.member.service.MemberListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group/{group_id}/member")
public class MemberController {

    private final MemberListService memberListService;

    @GetMapping
    public ResponseEntity<MemberListResponse> findAllMember(@PathVariable Long group_id) {
        MemberListResponse memberListResponse = memberListService.execute(group_id);
        return new ResponseEntity<>(memberListResponse, HttpStatus.OK);
    }
}
