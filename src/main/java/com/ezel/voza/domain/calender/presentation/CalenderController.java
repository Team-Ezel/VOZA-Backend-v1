package com.ezel.voza.domain.calender.presentation;

import com.ezel.voza.domain.calender.presentation.dto.request.CreateCalenderRequest;
import com.ezel.voza.domain.calender.presentation.dto.request.GetDateRequest;
import com.ezel.voza.domain.calender.presentation.dto.request.UpdateCalenderRequest;
import com.ezel.voza.domain.calender.presentation.dto.response.CalenderDayListResponse;
import com.ezel.voza.domain.calender.presentation.dto.response.CalenderDetailResponse;
import com.ezel.voza.domain.calender.presentation.dto.response.CalenderMonthListResponse;
import com.ezel.voza.domain.calender.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("group/{group_id}/calender")
public class CalenderController {

    private final CreateCalenderService createCalenderService;

    private final DeleteCalenderService deleteCalenderService;

    private final UpdateCalenderService updateCalenderService;

    private final CalenderMonthListService calenderMonthListService;

    private final CalenderDayListService calenderDayListService;

    private final CalenderDetailService calenderDetailService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateCalenderRequest createCalenderRequest, @PathVariable Long group_id) {
        createCalenderService.execute(createCalenderRequest, group_id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CalenderMonthListResponse> monthList(@PathVariable Long group_id, @RequestBody @Valid GetDateRequest getDateRequest) {
        CalenderMonthListResponse calenderMonthListResponse = calenderMonthListService.execute(group_id, getDateRequest);
        return new ResponseEntity<>(calenderMonthListResponse, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<CalenderDayListResponse> dayList(@PathVariable Long group_id, @RequestBody @Valid GetDateRequest getDateRequest) {
        CalenderDayListResponse calenderDayListResponse = calenderDayListService.execute(group_id, getDateRequest);
        return new ResponseEntity<>(calenderDayListResponse, HttpStatus.OK);
    }

    @GetMapping("/{calender_id}")
    public ResponseEntity<CalenderDetailResponse> findOne(@PathVariable Long calender_id) {
        CalenderDetailResponse calenderDetailResponse = calenderDetailService.execute(calender_id);
        return new ResponseEntity<>(calenderDetailResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{calender_id}")
    public ResponseEntity<Void> delete(@PathVariable Long calender_id) {
        deleteCalenderService.execute(calender_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{calender_id}")
    public ResponseEntity<Void> update(@RequestBody @Valid UpdateCalenderRequest updateCalenderRequest, @PathVariable Long calender_id) {
        updateCalenderService.execute(updateCalenderRequest, calender_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
