package com.ezel.voza.domain.calender.service.Impl;

import com.ezel.voza.domain.calender.entity.Calender;
import com.ezel.voza.domain.calender.presentation.dto.request.GetDateRequest;
import com.ezel.voza.domain.calender.presentation.dto.response.CalenderMonthListResponse;
import com.ezel.voza.domain.calender.presentation.dto.response.CalenderMonthResponse;
import com.ezel.voza.domain.calender.repository.CalenderRepository;
import com.ezel.voza.domain.calender.service.CalenderMonthListService;
import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.global.util.GroupUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalenderMonthListServiceImpl implements CalenderMonthListService {

    private final CalenderRepository calenderRepository;

    private final GroupUtil groupUtil;

    @Override
    public CalenderMonthListResponse execute(Long id, GetDateRequest getDateRequest) {

        Group group = groupUtil.findGroupById(id);

        List<Calender> calenderList = calenderRepository.findAllByGroupAndDate(group, getDateRequest.getDate());

        CalenderMonthListResponse calenderMonthListResponse = CalenderMonthListResponse.builder()
                .calenderMonthResponses(
                        calenderList.stream()
                                .map(CalenderMonthResponse::toResponse)
                                .collect(Collectors.toList())
                )
                .build();

        return calenderMonthListResponse;
    }
}
