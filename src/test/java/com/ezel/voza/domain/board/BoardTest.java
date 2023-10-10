package com.ezel.voza.domain.board;

import com.ezel.voza.domain.board.presentation.dto.request.CreateBoardRequest;
import com.ezel.voza.domain.board.presentation.dto.request.UpdateBoardRequest;
import com.ezel.voza.domain.user.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@MockBean(JpaMetamodelMappingContext.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class BoardTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    @DisplayName("테스트전 데이터 생성")
    void createImplData() {
        User member = User.builder()
                .email("kijkj123@gmail.com")
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
    @DisplayName("게시판 생성 테스트")
    void createBoard() throws Exception {

        CreateBoardRequest createBoardRequest = new CreateBoardRequest(
                "test title",
                "test content",
                "NORMAL"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String jsonRequest = objectMapper.writeValueAsString(createBoardRequest);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/group/1/board")
                .with(SecurityMockMvcRequestPostProcessors.user("MockUser"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        ).andDo(print())
                .andExpect(status().isCreated());
    }

    @WithMockUser("MockUser")
    @Test
    @DisplayName("게시판 리스트 테스트")
    void listBoard() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .get("/group/1/board")
                .with(SecurityMockMvcRequestPostProcessors.user("MockUser"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(jsonPath("$.boardList[*].title").isNotEmpty())
                .andExpect(jsonPath("$.boardList[*].author").isNotEmpty())
                .andExpect(jsonPath("$.boardList[*].boardType").isNotEmpty());
    }

    @WithMockUser("MockUser")
    @Test
    @DisplayName("게시판 디테일 테스트")
    void boardDetail() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .get("/group/1/board/1")
                .with(SecurityMockMvcRequestPostProcessors.user("MockUser"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(jsonPath("$.title").isNotEmpty())
                .andExpect(jsonPath("$.content").isNotEmpty())
                .andExpect(jsonPath("$.author").isNotEmpty());
    }

    @WithMockUser("MockUser")
    @Test
    @DisplayName("게시판 수정 테스트")
    void boardUpdate() throws Exception {

        UpdateBoardRequest updateBoardRequest = new UpdateBoardRequest(
                "updated title",
                "updated content"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String jsonRequest = objectMapper.writeValueAsString(updateBoardRequest);

        mockMvc.perform(MockMvcRequestBuilders
                .patch("/group/1/board/1")
                .with(SecurityMockMvcRequestPostProcessors.user("MockUser"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @WithMockUser("MockUser")
    @Test
    @DisplayName("게시판 삭제 테스트")
    void boardDelete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/group/1/board/3")
                .with(SecurityMockMvcRequestPostProcessors.user("MockUser"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isNoContent());
    }
}
