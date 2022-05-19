package com.nhnacademy.jdbc.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.jdbc.board.controller.IndexController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class IndexControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
    }

    @Test
    void indexPageTest() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().isOk())
               .andExpect(view().name("index/index"));
    }

    @Test
    void loginedAsGuestTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/")).andReturn();

        assertThat(mvcResult.getModelAndView().getModel().get("user")).isEqualTo("Guest");
    }

    @Test
    void loginedAsUserTest() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "user");

        MvcResult mvcResult = mockMvc.perform(get("/").session(session)).andReturn();

        assertThat(mvcResult.getModelAndView().getModel().get("user")).isEqualTo("user");
    }


}
