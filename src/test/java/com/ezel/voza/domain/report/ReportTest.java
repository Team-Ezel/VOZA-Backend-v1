package com.ezel.voza.domain.report;

import com.ezel.voza.domain.report.presentation.dto.request.CreateReportRequest;
import com.ezel.voza.domain.report.repository.ReportRepository;
import com.ezel.voza.domain.user.entity.User;
import com.ezel.voza.domain.user.repository.UserRepository;
import com.ezel.voza.global.util.UserUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@MockBean(JpaMetamodelMappingContext.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class ReportTest {

    @Autowired private ReportRepository reportRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private UserUtil userUtil;

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
    @DisplayName("리포트 생성 테스트")
    void createReport() throws Exception {

        CreateReportRequest createReportRequest = new CreateReportRequest(
                "test",
                "test",
                "USER"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String jsonRequest = objectMapper.writeValueAsString(createReportRequest);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/report")
                .with(SecurityMockMvcRequestPostProcessors.user("MockUser"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        ).andDo(print())
                .andExpect(status().isCreated());
    }

    @WithMockUser("MockUser")
    @Test
    @DisplayName("리포트 List 가져오기 테스트")
    @Order(1)
    void listReport() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .get("/admin/report/list")
                .with(SecurityMockMvcRequestPostProcessors.user("MockUser"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(jsonPath("$.reportResponseList[*].title").isNotEmpty());
    }

    @WithMockUser("MockUser")
    @Test
    @DisplayName("리포트 Detail 가져오기 테스트")
    void reportDetail() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .get("/admin/report/26")
                .with(SecurityMockMvcRequestPostProcessors.user("MockUser"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(jsonPath("$.title").isNotEmpty())
                .andExpect(jsonPath("$.content").isNotEmpty())
                .andExpect(jsonPath("$.name").isNotEmpty())
                .andExpect(jsonPath("$.reportType").isNotEmpty());
    }

    @WithMockUser("MockUser")
    @Test
    @DisplayName("리포트 수락시 응답값 테스트")
    @Order(2)
    void reportRespond() throws Exception {

        //when 이걸 호출했을떄
        mockMvc.perform(MockMvcRequestBuilders
                .post("/admin/report/14/approve")
                .with(SecurityMockMvcRequestPostProcessors.user("MockUser"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print());

        //then 잘 호출됬을경우 요청값이 삭제되 report 를 찾을시 오류가 발생해야한다.
        assertThrows(NoSuchElementException.class, () -> {
                reportRepository.findById(13L).get().getTitle();
        });
    }
}
