package com.ezel.voza.domain.calender.presentation;

import com.ezel.voza.domain.calender.presentation.dto.request.CreateCalenderRequest;
import com.ezel.voza.domain.calender.service.CreateCalenderService;
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

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CreateCalenderRequest createCalenderRequest, @PathVariable Long group_id) {
        createCalenderService.execute(createCalenderRequest, group_id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
