package com.ezel.voza.global.util;

import com.ezel.voza.domain.calender.Exception.CalenderNotFoundException;
import com.ezel.voza.domain.calender.entity.Calender;
import com.ezel.voza.domain.calender.repository.CalenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CalenderUtil {

    private final CalenderRepository calenderRepository;

    public Calender findVoteById(Long id) {

        return calenderRepository.findById(id)
                .orElseThrow(CalenderNotFoundException::new);
    }
}
