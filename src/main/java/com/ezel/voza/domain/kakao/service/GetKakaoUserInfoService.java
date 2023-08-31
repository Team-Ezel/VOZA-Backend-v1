package com.ezel.voza.domain.kakao.service;

import com.ezel.voza.domain.kakao.entity.KakaoToken;
import com.ezel.voza.domain.kakao.presentation.dto.response.KakaoTokenResponse;
import com.ezel.voza.domain.kakao.presentation.dto.response.KakaoUserInfoResponse;
import com.ezel.voza.domain.kakao.repository.KakaoTokenRepository;
import com.ezel.voza.domain.user.presentation.dto.request.SignUpRequest;
import com.ezel.voza.domain.user.service.UserSignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class GetKakaoUserInfoService {

    private final WebClient webClient;
    private static final String USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";
    private final GetKakaoTokenService getKakaoTokenService;
    private final UserSignupService userSignupService;
    private final KakaoTokenRepository kakaoTokenRepository;

    public KakaoUserInfoResponse getUserInfo(String code) {

        KakaoTokenResponse kakaoTokenResponse = getKakaoTokenService.execute(code);

        Flux<KakaoUserInfoResponse> response = webClient.get()
                .uri(USER_INFO_URI)
                .header("Authorization", "Bearer " + kakaoTokenResponse.getAccess_token())
                .retrieve()
                .bodyToFlux(KakaoUserInfoResponse.class);

        KakaoToken kakaoToken = new KakaoToken(response.blockFirst().getId(), kakaoTokenResponse.getAccess_token(), kakaoTokenResponse.getRefresh_token(), kakaoTokenResponse.getExpires_in().longValue());
        kakaoTokenRepository.save(kakaoToken);

        SignUpRequest signUpRequest = SignUpRequest.builder()
                .email(Objects.requireNonNull(response.blockFirst()).getKakao_account().getEmail())
                .profileUrl(Objects.requireNonNull(response.blockFirst()).getKakao_account().getProfile().getProfile_image_url())
                .nickName(Objects.requireNonNull(response.blockFirst()).getKakao_account().getProfile().getNickname())
                .build();

        userSignupService.execute(signUpRequest);

        return response.blockFirst();
    }
}
