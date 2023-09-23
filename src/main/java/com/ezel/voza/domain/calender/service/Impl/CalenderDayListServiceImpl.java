package com.ezel.voza.domain.calender.service.Impl;

import com.ezel.voza.domain.calender.entity.Calender;
import com.ezel.voza.domain.calender.presentation.dto.request.GetDateRequest;
import com.ezel.voza.domain.calender.presentation.dto.response.CalenderDayListResponse;
import com.ezel.voza.domain.calender.presentation.dto.response.CalenderDayResponse;
import com.ezel.voza.domain.calender.repository.CalenderRepository;
import com.ezel.voza.domain.calender.service.CalenderDayListService;
import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.global.util.GroupUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalenderDayListServiceImpl implements CalenderDayListService {

    private final CalenderRepository calenderRepository;

    private final GroupUtil groupUtil;

    @Override
    public CalenderDayListResponse execute(Long group_id, GetDateRequest getDateRequest) {

        Group group = groupUtil.findGroupById(group_id);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(getDateRequest.getDate(), formatter);

        List<Calender> calenderList = calenderRepository.findAll(group, date);

        CalenderDayListResponse calenderDayListResponse = CalenderDayListResponse.builder()
                .calenderDayResponses(
                        calenderList.stream()
                                .map(CalenderDayResponse::toResponse)
                                .collect(Collectors.toList())
                )
                .build();

        return calenderDayListResponse;
    }
}
