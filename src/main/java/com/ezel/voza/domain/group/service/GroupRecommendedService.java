package com.ezel.voza.domain.group.service;

import com.ezel.voza.domain.group.presentation.dto.response.GroupResponse;

import java.util.List;

public interface GroupRecommendedService {

    List<GroupResponse> execute();
}
