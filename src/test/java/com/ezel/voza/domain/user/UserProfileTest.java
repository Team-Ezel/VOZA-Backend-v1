package com.ezel.voza.domain.user;

import com.ezel.voza.domain.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@MockBean(JpaMetamodelMappingContext.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class UserProfileTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    @DisplayName("테스트전 데이터 생성")
    void createImplData() {
        User member = User.builder()
                .email("zadzed1100@gmail.com")
                .nickName("MockUser")
                .build();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                member.getEmail(),
                member.getNickName()
        );

        SecurityContextHolder.getContext().setAuthentication(token);
    }

    @WithMockUser("MockUser")
    @Test
    @DisplayName("유저 Profile 가져오기 Test")
    void reportDetail() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/profile")
                        .with(SecurityMockMvcRequestPostProcessors.user("MockUser"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(jsonPath("$.email").isNotEmpty())
                .andExpect(jsonPath("$.profileUrl").isNotEmpty())
                .andExpect(jsonPath("$.nickName").isNotEmpty());
    }
}
