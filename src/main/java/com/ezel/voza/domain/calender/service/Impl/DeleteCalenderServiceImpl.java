package com.ezel.voza.domain.calender.service.Impl;

import com.ezel.voza.domain.calender.exception.CalenderAuthorMismatchException;
import com.ezel.voza.domain.calender.entity.Calender;
import com.ezel.voza.domain.calender.repository.CalenderRepository;
import com.ezel.voza.domain.calender.service.DeleteCalenderService;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.global.util.CalenderUtil;
import com.ezel.voza.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DeleteCalenderServiceImpl implements DeleteCalenderService {

    private final CalenderRepository calenderRepository;

    private final CalenderUtil calenderUtil;

    private final UserUtil userUtil;

    @Override
    public void execute(Long id) {

        Calender calender = calenderUtil.findCalenderById(id);

        User user = userUtil.currentUser();

        if(!(Objects.equals(calender.getUser(), user))) {
            throw new CalenderAuthorMismatchException();
        }

        calenderRepository.delete(calender);
    }
}
