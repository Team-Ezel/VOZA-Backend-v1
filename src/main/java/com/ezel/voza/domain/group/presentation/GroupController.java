package com.ezel.voza.domain.group.presentation;

import com.ezel.voza.domain.group.presentation.dto.request.CreateGroupRequest;
import com.ezel.voza.domain.group.presentation.dto.request.EnterGroupRequest;
import com.ezel.voza.domain.group.presentation.dto.response.GroupListResponse;
import com.ezel.voza.domain.group.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {

    private final CreateGroupService createGroupService;
    private final CreateGroupInviteCode createGroupInviteCode;
    private final EnterGroupService enterGroupService;
    private final OutGroupService outGroupService;

    @Autowired
    @Qualifier("myGroupListService")
    private GroupListService myGroupListService;

    @Autowired
    @Qualifier("otherGroupListService")
    private GroupListService otherGroupListService;

    @PostMapping
    public ResponseEntity<Void> groupCreate(@RequestPart("data") @Valid CreateGroupRequest createGroupRequest, @RequestPart("file")MultipartFile file) {
        createGroupService.execute(createGroupRequest, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{groupId}")
    public ResponseEntity<?> createInviteCode(@PathVariable Long groupId) {
        String inviteCode = createGroupInviteCode.generateInviteCode(groupId);
        return new ResponseEntity<>(inviteCode, HttpStatus.CREATED);
    }

    @PostMapping("/enter")
    public ResponseEntity<Void> enterGroup(@RequestBody @Valid EnterGroupRequest enterGroupRequest) {
        enterGroupService.execute(enterGroupRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> outGroup(@PathVariable Long groupId) {
        outGroupService.execute(groupId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/myGroups")
    public ResponseEntity<GroupListResponse> getContainsGroupList() {
        var containsList = myGroupListService.execute();
        return new ResponseEntity<>(containsList, HttpStatus.OK);
    }

    @GetMapping("/otherGroups")
    public ResponseEntity<GroupListResponse> getNotContainsGroupList() {
        var notContainsList = otherGroupListService.execute();
        return new ResponseEntity<>(notContainsList, HttpStatus.OK);
    }
}
