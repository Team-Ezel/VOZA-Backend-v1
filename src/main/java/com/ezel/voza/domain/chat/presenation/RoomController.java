package com.ezel.voza.domain.chat.presenation;

import com.ezel.voza.domain.chat.service.CreateRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {

    private final CreateRoomService createRoomService;

    @PostMapping("/{groupId}")
    public ResponseEntity<Void> createRoom(@PathVariable String groupId) {
        createRoomService.execute(Long.valueOf(groupId));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
