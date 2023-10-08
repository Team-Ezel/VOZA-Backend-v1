package com.ezel.voza.domain.user;

import com.ezel.voza.domain.group.repository.GroupRepository;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.domain.user.entity.enums.Banned;
import com.ezel.voza.domain.user.entity.enums.Role;
import com.ezel.voza.domain.user.repository.UserRepository;
import com.ezel.voza.global.security.jwt.TokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@MockBean(JpaMetamodelMappingContext.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class AdminUserTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired private UserRepository userRepository;
    @Autowired private TokenProvider tokenProvider;
    @Autowired private GroupRepository groupRepository;

    @BeforeEach
    @DisplayName("테스트전 데이터 생성")
    void createImplData() {
        User member = User.builder()
                .email("sovietyone@kakao.com")
                .nickName("배선후")
                .build();

        Authentication authentication = tokenProvider.authentication(tokenProvider.generateAccessToken(member.getEmail()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @WithMockUser(roles = "ADMIN", username = "배선후")
    @Test
    @DisplayName("유저 Admin 권환 삭제 테스트")
    void revokeUserRoleTest() throws Exception {

        String email = "sovietyone@kakao.com";

        mockMvc.perform(MockMvcRequestBuilders
                .patch("/admin/revoke")
                .param("email", email)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());

        assertThat(userRepository.findByEmail(email).get().getRole()).isEqualTo(Role.ROLE_USER);
    }

    @WithMockUser(roles = "ADMIN", username = "배선후")
    @Test
    @DisplayName("유저 Admin 권환 부여 테스트")
    void grantUserRoleTest() throws Exception {

        String email = "sovietyone@kakao.com";

        mockMvc.perform(MockMvcRequestBuilders
                .patch("/admin/grant")
                .param("email", email)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());

        assertThat(userRepository.findByEmail(email).get().getRole()).isEqualTo(Role.ROLE_ADMIN);
    }

    @WithMockUser(roles = "ADMIN", username = "배선후")
    @Test
    @DisplayName("유저 Admin 유저 밴 기능 테스트")
    void adminBanUser() throws Exception {

        String email = "zadzed1100@gmail.com";

        mockMvc.perform(MockMvcRequestBuilders
                .patch("/admin/ban")
                .param("email", email)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());

        assertThat(userRepository.findByEmail(email).get().getBanned()).isEqualTo(Banned.BAN);
    }

    @WithMockUser(roles = "ADMIN", username = "배선후")
    @Test
    @DisplayName("유저 Admin 유저 언밴 기능 테스트")
    void adminUnBanUser() throws Exception {

        String email = "zadzed1100@gmail.com";

        mockMvc.perform(MockMvcRequestBuilders
                .patch("/admin/unban")
                .param("email", email)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());

        assertThat(userRepository.findByEmail(email).get().getBanned()).isEqualTo(Banned.UNBAN);
    }

    @WithMockUser(roles = "ADMIN", username = "배선후")
    @Test
    @DisplayName("Admin 그룹 밴 기능 테스트")
    void adminGroupBan() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .patch("/admin/groupBan/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());

        assertThat(groupRepository.findById(1L).get().getStop()).isEqualTo(true);
    }
}
