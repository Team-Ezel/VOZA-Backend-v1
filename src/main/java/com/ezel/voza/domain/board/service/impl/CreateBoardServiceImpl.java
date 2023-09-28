package com.ezel.voza.domain.board.service.impl;

import com.ezel.voza.domain.board.entity.Board;
import com.ezel.voza.domain.board.entity.enums.BoardType;
import com.ezel.voza.domain.board.presentation.dto.request.CreateBoardRequest;
import com.ezel.voza.domain.board.repository.BoardRepository;
import com.ezel.voza.domain.board.service.CreateBoardService;
import com.ezel.voza.domain.file.service.SingleFileUploadService;
import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.util.GroupUtil;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateBoardServiceImpl implements CreateBoardService {

    private final UserUtil util;

    private final BoardRepository boardRepository;

    private final GroupUtil groupUtil;

    private final SingleFileUploadService singleFileUploadService;

    @Value("${cloud.aws.s3.url}")
    private String AWS_S3_ADDRESS;

    @Override
    public void execute(CreateBoardRequest createBoardRequest, Long groupId, MultipartFile file) {

        String fileUrl = null;
        if (file != null) {
            fileUrl = singleFileUploadService.uploadFile(file);
        }

        User user = util.currentUser();

        Group group = groupUtil.findGroupById(groupId);

        Board board = Board.builder()
                .title(createBoardRequest.getTitle())
                .content(createBoardRequest.getContent())
                .boardType(BoardType.from(createBoardRequest.getBoardType()))
                .author(user.getNickName())
                .createdDate(LocalDateTime.now())
                .editedDate(LocalDateTime.now())
                .group(group)
                .url(AWS_S3_ADDRESS + fileUrl)
                .build();

        boardRepository.save(board);
    }
}
