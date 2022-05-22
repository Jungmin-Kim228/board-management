package com.nhnacademy.jdbc.board;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.nhnacademy.jdbc.board.compre.service.impl.DefaultLikeService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultPostService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultUserService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultViewService;
import com.nhnacademy.jdbc.board.controller.BoardController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class BoardControllerTest {
    private DefaultPostService postService;
    private DefaultUserService userService;
    private DefaultLikeService likeService;
    private DefaultViewService viewService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        postService = mock(DefaultPostService.class);
        userService = mock(DefaultUserService.class);
        likeService = mock(DefaultLikeService.class);
        viewService = mock(DefaultViewService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new BoardController(postService, userService, likeService, viewService)).build();
    }

    @Test
    void boardViewTest() throws Exception {

    }

    @Test
    void readyBoardRegisterTest() throws Exception {
        mockMvc.perform(get("/boardRegister"))
                                     .andExpect(view().name("board/boardRegisterForm"));
    }

    @Test
    void boardRegisterTest() throws Exception {

    }

    @Test
    void readyBoardModifyTest() throws Exception {

    }

    @Test
    void boardModifyTest() throws Exception {

    }

    @Test
    void boardDeleteTest() throws Exception {

    }

    @Test
    void recoverBoardViewTest() throws Exception {

    }

    @Test
    void boardRecoverTest() throws Exception {
        doNothing().when(postService).recover(anyInt());

        mockMvc.perform(post("/boardRecover/1"))
            .andExpect(view().name("redirect:/boardRecover"));
    }
}
