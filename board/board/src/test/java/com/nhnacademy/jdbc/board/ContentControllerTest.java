package com.nhnacademy.jdbc.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.jdbc.board.compre.dto.CommentDTO;
import com.nhnacademy.jdbc.board.compre.dto.PostDTO;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultCommentService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultPostService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultUserService;
import com.nhnacademy.jdbc.board.controller.ContentController;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ContentControllerTest {
    private MockMvc mockMvc;
    private DefaultPostService postService;
    private DefaultCommentService commentService;
    private DefaultUserService userService;

    @BeforeEach
    void setUp() {
        postService = mock(DefaultPostService.class);
        commentService = mock(DefaultCommentService.class);
        userService = mock(DefaultUserService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(
            new ContentController(commentService, userService, postService)).build();
    }

    @Test
    void readyBoardContentTest() throws Exception {
        PostDTO postDTO = new PostDTO("title", "content", new Date());
        CommentDTO commentDTO = new CommentDTO(1, "commentWriter", "commentContent");
        List<CommentDTO> commentDTOS = List.of(commentDTO);

        when(postService.getPost(anyInt())).thenReturn(Optional.of(postDTO));
        when(commentService.getComments(anyInt())).thenReturn(commentDTOS);
        MvcResult mvcResult = mockMvc.perform(get("/content")
                                         .param("id", "1"))
                                     .andExpect(view().name("content/boardContent"))
                                     .andExpect(status().isOk())
                                     .andReturn();

        assertThat(mvcResult.getModelAndView().getModel().get("post")).isInstanceOf(PostDTO.class);
        assertThat(mvcResult.getModelAndView().getModel().get("comment")).isInstanceOf(List.class);
    }

//    @Test
//    void commentRegisterTest() throws Exception {
//        MvcResult mvcResult = mockMvc.perform(post("/commentRegister")
//            .param("id", "1")
//            .param("comment", "commentContent"))
//            .andExpect()
//    }
//
//    @Test
//    void commentModifyOrDeleteTest() throws Exception {
//
//    }
//
//    @Test
//    void commentModifyTest() throws Exception {
//
//    }
}
