package com.ezel.voza.domain.calender.service.Impl;

import com.ezel.voza.domain.calender.entity.Calender;
import com.ezel.voza.domain.calender.presentation.dto.request.CreateCalenderRequest;
import com.ezel.voza.domain.calender.repository.CalenderRepository;
import com.ezel.voza.domain.calender.service.CreateCalenderService;
import com.ezel.voza.domain.group.entity.Group;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.util.GroupUtil;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCalenderServiceImpl implements CreateCalenderService {

    private final CalenderRepository calenderRepository;

    private final UserUtil userUtil;

    private final GroupUtil groupUtil;

    @Override
    public void execute(CreateCalenderRequest createCalenderRequest, Long id) {

        User user = userUtil.currentUser();

        Group group = groupUtil.findGroupById(id);

        Calender calender = Calender.builder()
                .title(createCalenderRequest.getTitle())
                .content(createCalenderRequest.getContent())
                .user(user)
                .date(createCalenderRequest.getStartDate().toString().substring(0,7))
                .startDate(createCalenderRequest.getStartDate())
                .endDate(createCalenderRequest.getEndDate())
                .group(group)
                .build();

        calenderRepository.save(calender);
    }
}
