package com.ezel.voza.domain.group.presentation;

import com.ezel.voza.domain.group.presentation.dto.request.CreateGroupRequest;
import com.ezel.voza.domain.group.presentation.dto.request.EnterGroupRequest;
import com.ezel.voza.domain.group.presentation.dto.response.GroupDetailResponse;
import com.ezel.voza.domain.group.presentation.dto.response.GroupListResponse;
import com.ezel.voza.domain.group.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {

    private final CreateGroupService createGroupService;
    private final CreateGroupInviteCode createGroupInviteCode;
    private final EnterByCodeGroupService enterByCodeGroupService;
    private final OutGroupService outGroupService;
    private final GroupDetailService groupDetailService;
    private final GroupListService myGroupListService;
    private final OtherGroupListService otherGroupListService;
    private final EnterGroupService enterGroupService;

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

    @PostMapping("/enter/security")
    public ResponseEntity<Void> enterGroup(@RequestBody @Valid EnterGroupRequest enterGroupRequest) {
        enterByCodeGroupService.execute(enterGroupRequest);
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
    public ResponseEntity<Page<GroupListResponse>> getNotContainsGroupList(@RequestParam(defaultValue = "0") int pageSize) {
        Pageable pageable = PageRequest.of(pageSize, 10);
        Page<GroupListResponse> page = otherGroupListService.execute(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @GetMapping("/detail/{groupId}")
    public ResponseEntity<GroupDetailResponse> groupDetail(@PathVariable Long groupId) {
        GroupDetailResponse response = groupDetailService.execute(groupId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/enter/{groupId}")
    public ResponseEntity<Void> enterGroup(@PathVariable Long groupId) {
        enterGroupService.execute(groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
