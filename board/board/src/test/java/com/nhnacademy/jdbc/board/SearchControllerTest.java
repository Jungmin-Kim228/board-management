package com.nhnacademy.jdbc.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.jdbc.board.compre.dto.ViewPostDTO;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultLikeService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultPostService;
import com.nhnacademy.jdbc.board.controller.SearchController;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class SearchControllerTest {
    private DefaultPostService postService;
    private DefaultLikeService likeService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        postService = mock(DefaultPostService.class);
        likeService = mock(DefaultLikeService.class);
        mockMvc =
            MockMvcBuilders.standaloneSetup(new SearchController(postService, likeService)).build();
    }

    @Test
    void searchBoardTestIfSessionIsNull() throws Exception {
        ViewPostDTO postDTO = new ViewPostDTO(1, "title", "writer", new Date(), null, 0, false);
        List<ViewPostDTO> postDTOS = List.of(postDTO);

        when(postService.searchPost(anyString())).thenReturn(postDTOS);

        MvcResult mvcResult = mockMvc.perform(post("/searchBoard")
                                         .param("searchTitle", "title"))
                                     .andExpect(view().name("search/searchView"))
                                     .andReturn();

        assertThat(postDTO.isLike()).isFalse();
        assertThat(mvcResult.getModelAndView().getModel().get("searchPosts")).isEqualTo(postDTOS);
    }

    @Test
    void searchBoardTestIfSessionIsNonNull() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "user");
        ViewPostDTO postDTO = new ViewPostDTO(1, "title", "writer", new Date(), null, 0, false);
        List<ViewPostDTO> postDTOS = List.of(postDTO);

        when(postService.searchPost(anyString())).thenReturn(postDTOS);
        when(likeService.userLike(anyInt(), anyString())).thenReturn(true);

        MvcResult mvcResult = mockMvc.perform(post("/searchBoard")
                                         .param("searchTitle", "title")
                                         .session(session))
                                     .andReturn();

        assertThat(postDTO.isLike()).isTrue();
    }
}
