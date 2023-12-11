package com.ezel.voza.domain.auth.presentation;

import com.ezel.voza.domain.auth.presentation.dto.response.NewTokenResponse;
import com.ezel.voza.domain.auth.service.ReIssueTokenService;
import com.ezel.voza.domain.auth.service.UserLogoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final ReIssueTokenService reIssueTokenService;

    private final UserLogoutService userLogoutService;

    @PatchMapping
    public ResponseEntity<NewTokenResponse> reIssueToken(@RequestHeader("Refresh-token") String refreshToken) {
        NewTokenResponse newTokenResponse = reIssueTokenService.execute(refreshToken);
        return new ResponseEntity<>(newTokenResponse, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> logout(
            @RequestHeader("Access-token") String accessToken,
            @RequestHeader("Refresh-token") String refreshToken)
    {
        userLogoutService.execute(accessToken, refreshToken);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
