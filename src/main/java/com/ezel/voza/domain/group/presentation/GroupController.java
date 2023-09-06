package com.ezel.voza.domain.group.presentation;

import com.ezel.voza.domain.group.presentation.dto.request.CreateGroupRequest;
import com.ezel.voza.domain.group.service.CreateGroupInviteCode;
import com.ezel.voza.domain.group.service.CreateGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {

    private final CreateGroupService createGroupService;

    private final CreateGroupInviteCode createGroupInviteCode;

    @PostMapping
    public ResponseEntity<Void> groupCreate(@RequestBody @Valid CreateGroupRequest createGroupRequest) {
        createGroupService.execute(createGroupRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{groupId}")
    public ResponseEntity<String> createInviteCode(@RequestParam UUID groupId) {
        String inviteCode = createGroupInviteCode.generateInviteCode(groupId);
        return new ResponseEntity<>(inviteCode, HttpStatus.CREATED);
    }
}
