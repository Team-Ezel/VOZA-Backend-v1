package com.ezel.voza.domain.member.presentation;

import com.ezel.voza.domain.member.presentation.response.MemberListResponse;
import com.ezel.voza.domain.member.service.KickOutMemberService;
import com.ezel.voza.domain.member.service.MemberListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group/{group_id}/member")
public class MemberController {

    private final MemberListService memberListService;

    private final KickOutMemberService kickOutMemberService;

    @GetMapping
    public ResponseEntity<MemberListResponse> findAllMember(@PathVariable Long group_id) {
        MemberListResponse memberListResponse = memberListService.execute(group_id);
        return new ResponseEntity<>(memberListResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> kickOutMember(@PathVariable Long group_id, @PathVariable Long user_id) {
        kickOutMemberService.execute(group_id, user_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
