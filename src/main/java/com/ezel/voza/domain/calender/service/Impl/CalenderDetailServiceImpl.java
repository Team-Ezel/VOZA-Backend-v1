package com.ezel.voza.domain.calender.service.Impl;

import com.ezel.voza.domain.calender.entity.Calender;
import com.ezel.voza.domain.calender.presentation.dto.response.CalenderDetailResponse;
import com.ezel.voza.domain.calender.service.CalenderDetailService;
import com.ezel.voza.global.annotation.ReadOnlyService;
import com.ezel.voza.global.util.CalenderUtil;
import lombok.RequiredArgsConstructor;

@ReadOnlyService
@RequiredArgsConstructor
public class CalenderDetailServiceImpl implements CalenderDetailService {

    private final CalenderUtil calenderUtil;

    @Override
    public CalenderDetailResponse execute(Long id) {

        Calender calender = calenderUtil.findCalenderById(id);

        return CalenderDetailResponse.builder()
                .id(calender.getId())
                .title(calender.getTitle())
                .content(calender.getContent())
                .author(calender.getUser().getNickName())
                .startDate(calender.getStartDate())
                .endDate(calender.getEndDate())
                .build();
    }
}
