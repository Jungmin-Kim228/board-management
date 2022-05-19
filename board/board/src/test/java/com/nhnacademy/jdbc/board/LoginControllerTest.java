package com.nhnacademy.jdbc.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.nhnacademy.jdbc.board.compre.service.impl.DefaultUserService;
import com.nhnacademy.jdbc.board.controller.LoginController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class LoginControllerTest {
    private MockMvc mockMvc;
    private DefaultUserService userService;

    @BeforeEach
    void setUp() {
        userService = mock(DefaultUserService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new LoginController(userService)).build();
    }

    @Test
    void loginTest() throws Exception {
        String id = "user";
        String pw = "useruser";

        when(userService.successLogin(id, pw)).thenReturn(true);

        MvcResult mvcResult = mockMvc.perform(
            post("/login")
                .param("logId", id)
                .param("logPw", pw)).andReturn();

        assertThat(mvcResult.getModelAndView().getViewName()).isEqualTo("index/index");
        assertThat(mvcResult.getRequest().getSession(false).getAttribute("id")).isEqualTo("user");
    }

    @Test
    void loginFailedTest() throws Exception {
        String id = "user";
        String pw = "uuuu";

        when(userService.successLogin(id, pw)).thenReturn(false);

        MvcResult mvcResult = mockMvc.perform(
            post("/login")
                .param("logId", id)
                .param("logPw", pw)).andReturn();

        assertThat(mvcResult.getModelAndView().getViewName()).isEqualTo("redirect:/login");
        assertThat(mvcResult.getRequest().getSession(false).getAttribute("id")).isNull();
    }

    @Test
    void logoutTest() throws Exception {
        
    }
}
