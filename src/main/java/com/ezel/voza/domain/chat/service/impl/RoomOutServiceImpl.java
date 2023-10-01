package com.ezel.voza.domain.chat.service.impl;

import com.ezel.voza.domain.chat.entity.Room;
import com.ezel.voza.domain.chat.exception.NotExistRoomUserException;
import com.ezel.voza.domain.chat.repository.RoomRepository;
import com.ezel.voza.domain.chat.service.RoomOutService;
import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.util.GroupUtil;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomOutServiceImpl implements RoomOutService {

    private final UserUtil userUtil;
    private final RoomRepository roomRepository;

    @Override
    public void execute(Long groupId, Group group) {
        User user = userUtil.currentUser();
        checkExistRoom(user, group);

        Room room = roomRepository.findByGroup(group);

        room.updateLastChat(user.getNickName() + "님이 탈퇴하셨습니다.");
        roomRepository.save(room);
    }

    private void checkExistRoom(User user, Group group) {
        if (!group.getMembers().containsKey(user)) {
            throw new NotExistRoomUserException();
        }
    }
}
