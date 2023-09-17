package com.ezel.voza.domain.user.presentation;

import com.ezel.voza.domain.user.presentation.dto.response.ProfileResponse;
import com.ezel.voza.domain.user.service.GetProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final GetProfileService getProfileService;

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> getProfile() {
        ProfileResponse profileResponse = getProfileService.execute();
        return new ResponseEntity<>(profileResponse, HttpStatus.OK);
    }
}
