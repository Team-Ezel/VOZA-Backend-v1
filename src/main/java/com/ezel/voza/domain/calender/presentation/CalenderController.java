package com.ezel.voza.domain.calender.presentation;

import com.ezel.voza.domain.calender.presentation.dto.request.CreateCalenderRequest;
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
@RequestMapping("group/{groupId}/calender")
public class CalenderController {

    private final CreateCalenderService createCalenderService;

    private final DeleteCalenderService deleteCalenderService;

    private final UpdateCalenderService updateCalenderService;

    private final CalenderMonthListService calenderMonthListService;

    private final CalenderDayListService calenderDayListService;

    private final CalenderDetailService calenderDetailService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateCalenderRequest createCalenderRequest, @PathVariable Long groupId) {
        createCalenderService.execute(createCalenderRequest, groupId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CalenderMonthListResponse> monthList(@PathVariable Long groupId, @RequestParam String searchDate) {
        CalenderMonthListResponse calenderMonthListResponse = calenderMonthListService.execute(groupId, searchDate);
        return new ResponseEntity<>(calenderMonthListResponse, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<CalenderDayListResponse> dayList(@PathVariable Long groupId, @RequestParam String searchDate) {
        CalenderDayListResponse calenderDayListResponse = calenderDayListService.execute(groupId, searchDate);
        return new ResponseEntity<>(calenderDayListResponse, HttpStatus.OK);
    }

    @GetMapping("/{calenderId}")
    public ResponseEntity<CalenderDetailResponse> findOne(@PathVariable Long calenderId) {
        CalenderDetailResponse calenderDetailResponse = calenderDetailService.execute(calenderId);
        return new ResponseEntity<>(calenderDetailResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{calenderId}")
    public ResponseEntity<Void> delete(@PathVariable Long calenderId) {
        deleteCalenderService.execute(calenderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{calenderId}")
    public ResponseEntity<Void> update(@RequestBody @Valid UpdateCalenderRequest updateCalenderRequest, @PathVariable Long calenderId) {
        updateCalenderService.execute(updateCalenderRequest, calenderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
