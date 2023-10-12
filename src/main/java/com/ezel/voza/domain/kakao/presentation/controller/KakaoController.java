package com.ezel.voza.domain.kakao.presentation.controller;

import com.ezel.voza.domain.auth.presentation.dto.response.LoginResponse;
import com.ezel.voza.domain.auth.service.UserLoginService;
import com.ezel.voza.domain.kakao.presentation.dto.response.KakaoLogoutUserIdResponse;
import com.ezel.voza.domain.kakao.service.GetKakaoUserInfoService;
import com.ezel.voza.domain.kakao.service.KakaoLogoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class KakaoController {

    private final GetKakaoUserInfoService getKakaoUserInfoService;
    private final KakaoLogoutService kakaoLogoutService;
    private final UserLoginService userLoginService;

    @GetMapping("/oauth2/kakao")
    public ResponseEntity<LoginResponse> getUserInfo(@RequestParam("code") String code) {
        LoginResponse loginResponse = userLoginService.execute(getKakaoUserInfoService.getUserInfo(code).getKakao_account().getEmail());
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @DeleteMapping("/oauth2/logout")
    public ResponseEntity<KakaoLogoutUserIdResponse> kakaoLogout(@RequestHeader("Authorization") String accessToken, @RequestParam("userId") Long userId) {
        kakaoLogoutService.execute(userId, accessToken);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
