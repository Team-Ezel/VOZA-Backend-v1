package com.ezel.voza.domain.member.presentation;

import com.ezel.voza.domain.member.presentation.response.MemberListResponse;
import com.ezel.voza.domain.member.service.GrantLeaderRoleService;
import com.ezel.voza.domain.member.service.KickOutMemberService;
import com.ezel.voza.domain.member.service.MemberListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group/{groupId}/member")
public class MemberController {

    private final MemberListService memberListService;

    private final KickOutMemberService kickOutMemberService;

    private final GrantLeaderRoleService grantLeaderRoleService;

    @GetMapping
    public ResponseEntity<MemberListResponse> findAllMember(@PathVariable Long groupId) {
        MemberListResponse memberListResponse = memberListService.execute(groupId);
        return new ResponseEntity<>(memberListResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> kickOutMember(
            @PathVariable Long groupId,
            @PathVariable Long userId,
            @RequestParam String kickOutTime
    ) {
        kickOutMemberService.execute(groupId, userId, kickOutTime);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/grant/{userId}")
    public ResponseEntity<Void> grantLeaderRole(@PathVariable Long groupId, @PathVariable Long userId) {
        grantLeaderRoleService.execute(groupId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
