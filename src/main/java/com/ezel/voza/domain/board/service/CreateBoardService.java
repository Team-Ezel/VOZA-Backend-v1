package com.ezel.voza.domain.board.service;

import com.ezel.voza.domain.board.presentation.dto.request.CreateBoardRequest;
import org.springframework.web.multipart.MultipartFile;

public interface CreateBoardService {

    void execute(CreateBoardRequest createBoardRequest, Long groupId, MultipartFile file);
}
