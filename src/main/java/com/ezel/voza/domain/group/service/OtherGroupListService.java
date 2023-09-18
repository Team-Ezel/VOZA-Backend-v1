package com.ezel.voza.domain.group.service;

import com.ezel.voza.domain.group.presentation.dto.response.GroupListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OtherGroupListService {

    Page<GroupListResponse> execute(Pageable pageable);
}
