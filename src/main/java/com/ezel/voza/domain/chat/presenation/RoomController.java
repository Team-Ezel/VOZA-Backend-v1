package com.ezel.voza.domain.chat.presenation;

import com.ezel.voza.domain.chat.presenation.dto.response.ChatListResponse;
import com.ezel.voza.domain.chat.service.CreateRoomService;
import com.ezel.voza.domain.chat.service.FindAllChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {

    private final CreateRoomService createRoomService;
    private final FindAllChatService findAllChatService;

    @PostMapping("/{groupId}")
    public ResponseEntity<Void> createRoom(@PathVariable String groupId) {
        createRoomService.execute(Long.valueOf(groupId));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<ChatListResponse> findAllChatByRoom(@PathVariable Long groupId) {
        var map = findAllChatService.execute(groupId);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
