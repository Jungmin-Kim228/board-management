package com.nhnacademy.jdbc.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.jdbc.board.compre.dto.PostDTO;
import com.nhnacademy.jdbc.board.compre.dto.ViewPostDTO;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultLikeService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultPostService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultUserService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultViewService;
import com.nhnacademy.jdbc.board.controller.BoardController;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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
        mockMvc = MockMvcBuilders.standaloneSetup(
            new BoardController(postService, userService, likeService, viewService)).build();
    }

    @Test
    void boardViewTest() throws Exception {
        PostDTO postDTO =
            new PostDTO(1, "title", "writer", "content", new Date(), null, 0, false, 0, 0);
        List<PostDTO> list = List.of(postDTO);
        List<View> views = new ArrayList<>();
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "user");

        when(postService.getCount()).thenReturn(1);
        when(postService.getListPage(any())).thenReturn(list);
        when(likeService.userLike(anyInt(), anyString())).thenReturn(true);
        when(viewService.findView(anyInt())).thenReturn(views);

        MvcResult mvcResult = mockMvc.perform(get("/board")
                                         .param("page", "1")
                                         .session(session))
                                     .andExpect(view().name("board/boardView"))
                                     .andReturn();

        assertThat(mvcResult.getModelAndView().getModel().get("allPost")).isInstanceOf(List.class);
        assertThat(mvcResult.getModelAndView().getModel().get("page")).isEqualTo(1);
        assertThat(mvcResult.getModelAndView().getModel().get("pagination")).isInstanceOf(
            Pagination.class);
        assertThat(postDTO.isLike()).isTrue();
    }

    @Test
    void checkUserLikedInBoardViewTest() throws Exception {
        PostDTO postDTO =
            new PostDTO(1, "title", "writer", "content", new Date(), null, 0, false, 0, 0);
        List<PostDTO> list = List.of(postDTO);
        List<View> views = new ArrayList<>();

        when(postService.getCount()).thenReturn(1);
        when(postService.getListPage(any())).thenReturn(list);
        when(likeService.userLike(anyInt(), anyString())).thenReturn(true);
        when(viewService.findView(anyInt())).thenReturn(views);

        mockMvc.perform(get("/board")
            .param("page", "1"));

        assertThat(postDTO.isLike()).isFalse();
        // postDTO의 isLike가 false로 바뀌었으나 BoardController의 78라인이 cover되지 않음
    }

    @Test
    void readyBoardRegisterTest() throws Exception {
        mockMvc.perform(get("/boardRegister"))
               .andExpect(view().name("board/boardRegisterForm"));
    }

    @Test
    void boardRegisterIfFileIsNonNullTest() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", new byte[10]);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "user");

        when(userService.getUser(anyString())).thenReturn(1);
        doNothing().when(postService).register(any(PostDTO.class), anyInt());

        mockMvc.perform(multipart("/boardRegister")
                   .file(file)
                   .param("writeTitle", "title")
                   .param("writeContent", "content")
                   .session(session))
               .andExpect(view().name("redirect:/board"));
    }

    @Test
    void boardRegisterIfFileIsNullTest() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "user");

        when(userService.getUser(anyString())).thenReturn(1);
        doNothing().when(postService).register(any(PostDTO.class), anyInt());

        mockMvc.perform(multipart("/boardRegister")
                   .param("writeTitle", "title")
                   .param("writeContent", "content")
                   .session(session))
               .andExpect(view().name("redirect:/board"));
    }

    @Test
    void boardRepostIfFileIsNonNullTest() throws Exception {
        PostDTO postDTO = new PostDTO();
        postDTO.setDepth(1);
        MockMultipartFile file = new MockMultipartFile("file", new byte[10]);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "user");

        when(postService.getPost(anyInt())).thenReturn(Optional.of(postDTO));
        when(userService.getUser(anyString())).thenReturn(1);
        doNothing().when(postService).register(any(PostDTO.class), anyInt());

        mockMvc.perform(multipart("/boardRepost/1")
                   .file(file)
                   .param("writeTitle", "title")
                   .param("writeContent", "content")
                   .session(session))
               .andExpect(view().name("redirect:/board"));
    }

    @Test
    void boardRepostIfFileIsNullTest() throws Exception {
        PostDTO postDTO = new PostDTO();
        postDTO.setDepth(1);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "user");

        when(postService.getPost(anyInt())).thenReturn(Optional.of(postDTO));
        when(userService.getUser(anyString())).thenReturn(1);
        doNothing().when(postService).register(any(PostDTO.class), anyInt());

        mockMvc.perform(multipart("/boardRepost/1")
                   .param("writeTitle", "title")
                   .param("writeContent", "content")
                   .session(session))
               .andExpect(view().name("redirect:/board"));
    }

    @Test
    void readyBoardRepost() throws Exception {
        MvcResult mvcResult =
            mockMvc.perform(get("/boardRepost/1"))
                .andExpect(view().name("/board/boardRepostForm"))
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModel().get("postNo")).isInstanceOf(Integer.class);

    }

    @Test
    void readyBoardModifyTest() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "user");
        Timestamp timestamp = new Timestamp(new Date().getTime());
        PostDTO postDTO = new PostDTO("1", "1", timestamp, 1, 1);
        postDTO.setWriter("user");

        when(postService.getPost(anyInt())).thenReturn(Optional.of(postDTO));

        MvcResult mvcResult =
            mockMvc.perform(get("/boardModify/1").session(session))
                .andExpect(view().name("board/boardModifyForm"))
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModel().get("post")).isInstanceOf(PostDTO.class);
    }

    @Test
    void failReadyBoardModifyTest() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "user");
        Timestamp timestamp = new Timestamp(new Date().getTime());
        PostDTO postDTO = new PostDTO("1", "1", timestamp, 1, 1);

        when(postService.getPost(anyInt())).thenReturn(Optional.of(postDTO));

        mockMvc.perform(get("/boardModify/1").session(session))
            .andExpect(view().name("redirect:/content?id=1"));
    }

    @Test
    void boardModifyTest() throws Exception {
        doNothing().when(postService).update(anyInt(), anyString(), anyString(), any());
        mockMvc.perform(post("/boardModify/1")
                .param("modifyTitle", "op")
                .param("modifyContent","operation"))
            .andExpect(view().name("redirect:/board"));
    }

    @Test
    void boardDeleteTest() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "user");
        Timestamp timestamp = new Timestamp(new Date().getTime());
        PostDTO postDTO = new PostDTO("1", "1", timestamp, 1, 1);


        when(userService.checkAdmin(anyInt())).thenReturn(true);
        when(userService.getUser(anyString())).thenReturn(1);
        when(postService.getPost(anyInt())).thenReturn(Optional.of(postDTO));
        doNothing().when(postService).delete(anyInt());

        mockMvc.perform(post("/boardDelete/1").session(session))
            .andExpect(view().name("redirect:/board"));
    }

    @Test
    void failBoardDeleteTest() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "user");
        Timestamp timestamp = new Timestamp(new Date().getTime());

        PostDTO postDTO = new PostDTO("1", "1", timestamp, 1, 1);

        when(userService.checkAdmin(anyInt())).thenReturn(false);
        when(userService.getUser(anyString())).thenReturn(1);
        when(postService.getPost(anyInt())).thenReturn(Optional.of(postDTO));

        mockMvc.perform(post("/boardDelete/1").session(session))
            .andExpect(view().name("redirect:/content?id=1"));
    }

    @Test
    void recoverBoardViewTest() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "user");
        Timestamp timestamp = new Timestamp(new Date().getTime());

        List<ViewPostDTO> postDTOS = new ArrayList<>();
        postDTOS.add(new ViewPostDTO("1", timestamp));

        when(userService.checkAdmin(anyInt())).thenReturn(true);
        when(userService.getUser(anyString())).thenReturn(1);
        when(postService.getPosts()).thenReturn(postDTOS);

        MvcResult mvcResult =
            mockMvc.perform(get("/boardRecover").session(session))
                .andExpect(view().name("board/boardRecover"))
                .andReturn();

        assertThat(mvcResult.getModelAndView().getModel().get("recoverPost")).isInstanceOf(List.class);
    }

    @Test
    void failRecoverBoardViewTest() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "user");

        when(userService.checkAdmin(anyInt())).thenReturn(false);
        when(userService.getUser(anyString())).thenReturn(1);

        mockMvc.perform(get("/boardRecover").session(session))
            .andExpect(view().name("redirect:/board"));


    }

    @Test
    void boardRecoverTest() throws Exception {
        doNothing().when(postService).recover(anyInt());

        mockMvc.perform(post("/boardRecover/1"))
               .andExpect(view().name("redirect:/boardRecover"));
    }
}
