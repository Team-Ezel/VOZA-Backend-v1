package com.ezel.voza.domain.kakao.service;

import com.ezel.voza.domain.kakao.entity.KakaoToken;
import com.ezel.voza.domain.kakao.presentation.dto.response.KakaoTokenResponse;
import com.ezel.voza.domain.kakao.presentation.dto.response.KakaoUserInfoResponse;
import com.ezel.voza.domain.kakao.repository.KakaoTokenRepository;
import com.ezel.voza.domain.user.presentation.dto.request.SignUpRequest;
import com.ezel.voza.domain.user.repository.UserRepository;
import com.ezel.voza.domain.user.service.UserSignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class GetKakaoUserInfoService {

    private final WebClient webClient;
    private static final String USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";
    private final GetKakaoTokenService getKakaoTokenService;
    private final UserSignupService userSignupService;
    private final KakaoTokenRepository kakaoTokenRepository;
    private final UserRepository userRepository;

    public KakaoUserInfoResponse getUserInfo(String code) {
        KakaoTokenResponse kakaoTokenResponse = getKakaoTokenService.execute(code);
        Flux<KakaoUserInfoResponse> response = fetchKakaoUserInfo(kakaoTokenResponse.getAccess_token());

        String email = getEmailFromResponse(response);
        if (email != null && !userRepository.existsByEmail(email)) {
            SignUpRequest signUpRequest = buildSignUpRequest(response, email);
            userSignupService.execute(signUpRequest);
        }

        saveKakaoToken(kakaoTokenResponse, Objects.requireNonNull(response.blockFirst()));

        return response.blockFirst();
    }

    private Flux<KakaoUserInfoResponse> fetchKakaoUserInfo(String accessToken) {
        return webClient.get()
                .uri(USER_INFO_URI)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToFlux(KakaoUserInfoResponse.class);
    }

    private String getEmailFromResponse(Flux<KakaoUserInfoResponse> response) {
        KakaoUserInfoResponse userInfo = response.blockFirst();
        return userInfo != null ? userInfo.getKakao_account().getEmail() : null;
    }

    private void saveKakaoToken(KakaoTokenResponse kakaoTokenResponse, KakaoUserInfoResponse userInfo) {
        KakaoToken kakaoToken = new KakaoToken(
                userInfo.getId(),
                kakaoTokenResponse.getAccess_token(),
                kakaoTokenResponse.getRefresh_token(),
                kakaoTokenResponse.getExpires_in().longValue()
        );
        kakaoTokenRepository.save(kakaoToken);
    }

    private SignUpRequest buildSignUpRequest(Flux<KakaoUserInfoResponse> response, String email) {
        KakaoUserInfoResponse userInfo = response.blockFirst();
        return SignUpRequest.builder()
                .email(email)
                .profileUrl(Objects.requireNonNull(userInfo).getKakao_account().getProfile().getProfile_image_url())
                .nickName(userInfo.getKakao_account().getProfile().getNickname())
                .build();
    }

}
