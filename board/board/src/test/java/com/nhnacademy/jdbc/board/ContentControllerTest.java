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

import com.nhnacademy.jdbc.board.compre.domain.Comment;
import com.nhnacademy.jdbc.board.compre.dto.CommentDTO;
import com.nhnacademy.jdbc.board.compre.dto.PostDTO;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultCommentService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultPostService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultUserService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultViewService;
import com.nhnacademy.jdbc.board.controller.ContentController;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ContentControllerTest {
    private DefaultPostService postService;
    private DefaultCommentService commentService;
    private DefaultUserService userService;
    private DefaultViewService viewService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        postService = mock(DefaultPostService.class);
        commentService = mock(DefaultCommentService.class);
        userService = mock(DefaultUserService.class);
        viewService = mock(DefaultViewService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(
            new ContentController(commentService, userService, postService, viewService)).build();
    }

    @Test
    void readyBoardContentTest() throws Exception {
        PostDTO postDTO = new PostDTO("title", "content", new Date(), 0, 0);
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

    @Test
    void commentRegisterTest() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "user");

        doNothing().when(commentService).register(anyInt(), anyInt(), anyString());

        mockMvc.perform(post("/commentRegister")
                   .param("id", "1")
                   .param("comment", "commentContent")
                   .session(session))
               .andExpect(view().name("redirect:/content?id=1"));
    }

    @Test
    void commentModifyOrDeleteIfButtonIsModifyTest() throws Exception {
        Comment comment = new Comment();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "user");

        when(commentService.getComment(anyInt())).thenReturn(Optional.of(comment));
        when(userService.getUserId(anyInt())).thenReturn("user");

        MvcResult mvcResult = mockMvc.perform(post("/comment/1")
                                         .param("button", "Modify")
                                         .session(session))
                                     .andExpect(view().name("comment/commentModify"))
                                     .andReturn();

        assertThat(mvcResult.getModelAndView().getModel().get("modifyComment")).isInstanceOf(
            CommentDTO.class);
    }

    @Test
    void commentModifyOrDeleteIfButtonIsDeleteTest() throws Exception {
        Comment comment = new Comment();
        comment.setCommentNo(1);
        comment.setPostNo(1);
        comment.setUserNo(1);
        comment.setCommentContent("commentContent");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "user");

        when(commentService.getComment(anyInt())).thenReturn(Optional.of(comment));
        when(userService.getUserId(anyInt())).thenReturn("user");
        doNothing().when(commentService).delete(anyInt());

        mockMvc.perform(post("/comment/1")
                                         .param("button", "Delete")
                                         .session(session))
                                     .andExpect(view().name("redirect:/content?id=1"));
    }

    @Test
    void commentModifyOrDeleteIfNotAutorizedTest() throws Exception {
        Comment comment = new Comment();
        comment.setCommentNo(1);
        comment.setPostNo(1);
        comment.setUserNo(1);
        comment.setCommentContent("commentContent");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "differentUser");

        when(commentService.getComment(anyInt())).thenReturn(Optional.of(comment));
        when(userService.getUserId(anyInt())).thenReturn("user");

        mockMvc.perform(post("/comment/1")
            .param("button", "modify")
            .session(session))
            .andExpect(view().name("redirect:/content?id=1"));
    }

    @Test
    void commentModifyTest() throws Exception {
        Comment comment = new Comment();
        comment.setCommentNo(1);
        comment.setPostNo(1);
        comment.setUserNo(1);
        comment.setCommentContent("oldCommentContent");

        doNothing().when(commentService).update(anyInt(), anyString());
        when(commentService.getComment(1)).thenReturn(Optional.of(comment));

        mockMvc.perform(post("/commentModify/1")
            .param("commentContent", "newCommentContent"))
            .andExpect(view().name("redirect:/content?id=1"));

    }
}
