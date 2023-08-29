package com.ezel.voza.domain.kakao.presentation.controller;


import com.ezel.voza.domain.kakao.presentation.dto.response.KakaoUserInfoResponse;
import com.ezel.voza.domain.kakao.service.GetKakaoUserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KakaoController {

    private final GetKakaoUserInfoService getKakaoUserInfoService;

    @GetMapping("/oauth2/kakao")
    public ResponseEntity<KakaoUserInfoResponse> getUserInfo(@RequestParam("code") String code) {
        KakaoUserInfoResponse kakaoUserInfoResponse = getKakaoUserInfoService.getUserInfo(code);
        return new ResponseEntity<>(kakaoUserInfoResponse, HttpStatus.OK);
    }
}
