package com.ezel.voza.domain.kakao.service;

import com.ezel.voza.domain.kakao.entity.KakaoToken;
import com.ezel.voza.domain.kakao.presentation.dto.response.KakaoLogoutUserIdResponse;
import com.ezel.voza.domain.kakao.repository.KakaoTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class KakaoLogoutService {

    private final WebClient webClient;
    private final KakaoTokenRepository kakaoTokenRepository;

    private static final String USER_LOGOUT_URI = "https://kapi.kakao.com/v1/user/logout";

    public KakaoLogoutUserIdResponse execute(Long userId, String token) {

        //TODO: Custom Exception 으로 교체하기
        KakaoToken kakaoToken = kakaoTokenRepository.findById(userId)
                .orElseThrow(RuntimeException::new);


        kakaoTokenRepository.delete(kakaoToken);

        String accessToken = kakaoToken.getAccessToken();

        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("target_id_type", "user_id");
        formData.add("target_id", userId);

        Flux<KakaoLogoutUserIdResponse> response = webClient.post()
                .uri(USER_LOGOUT_URI)
                .header("Authorization", "Bearer " + accessToken)
                .bodyValue(formData)
                .retrieve()
                .bodyToFlux(KakaoLogoutUserIdResponse.class);

        return response.blockFirst();
    }
}
