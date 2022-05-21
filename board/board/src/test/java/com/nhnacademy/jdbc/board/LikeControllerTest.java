package com.nhnacademy.jdbc.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.jdbc.board.compre.dto.ViewPostDTO;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultLikeService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultPostService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultUserService;
import com.nhnacademy.jdbc.board.controller.LikeController;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class LikeControllerTest {
    private DefaultLikeService likeService;
    private DefaultPostService postService;
    private DefaultUserService userService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        likeService = mock(DefaultLikeService.class);
        postService = mock(DefaultPostService.class);
        userService = mock(DefaultUserService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(
            new LikeController(likeService, postService, userService)).build();
    }

    @Test
    void likesViewTest() throws Exception {
        ViewPostDTO viewPostDTO = new ViewPostDTO(1, "title", "writer", new Date(), null, 0, false);
        List<ViewPostDTO> postDTOS = List.of(viewPostDTO);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "user");

        when(postService.getPosts()).thenReturn(postDTOS);
        when(likeService.userLike(anyInt(), anyString())).thenReturn(true);

        MvcResult mvcResult = mockMvc.perform(get("/boardLikes")
                                         .session(session))
                                     .andExpect(view().name("like/likeView"))
                                     .andExpect(status().isOk())
                                     .andReturn();

        assertThat(mvcResult.getModelAndView().getModel().get("likePost")).isEqualTo(postDTOS);
    }

    @Test
    void insertOrDeleteLikeIfButtonValueIsLike() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "user");

        doNothing().when(likeService).likeInsert(anyInt(), anyInt());

        mockMvc.perform(post("/like/1")
                   .param("button", "like")
                   .session(session))
               .andExpect(view().name("redirect:/board"));
    }

    @Test
    void insertOrDeleteLikeIfButtonValueIsNotLike() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "user");

        doNothing().when(likeService).likeInsert(anyInt(), anyInt());

        mockMvc.perform(post("/like/1")
                   .param("button", "unlike")
                   .session(session))
               .andExpect(view().name("redirect:/board"));
    }
}
