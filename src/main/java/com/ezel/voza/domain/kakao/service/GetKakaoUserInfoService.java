package com.ezel.voza.domain.kakao.service;

import com.ezel.voza.domain.kakao.presentation.dto.response.KakaoTokenResponse;
import com.ezel.voza.domain.kakao.presentation.dto.response.KakaoUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class GetKakaoUserInfoService {

    private final WebClient webClient;
    private static final String USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";
    private final GetKakaoTokenService getKakaoTokenService;

    public KakaoUserInfoResponse getUserInfo(String code) {

        KakaoTokenResponse kakaoTokenResponse = getKakaoTokenService.execute(code);

        Flux<KakaoUserInfoResponse> response = webClient.get()
                .uri(USER_INFO_URI)
                .header("Authorization", "Bearer " + kakaoTokenResponse.getAccess_token())
                .retrieve()
                .bodyToFlux(KakaoUserInfoResponse.class);

        return response.blockFirst();
    }
}
