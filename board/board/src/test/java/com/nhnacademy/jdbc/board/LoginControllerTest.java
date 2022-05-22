package com.nhnacademy.jdbc.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.jdbc.board.compre.dto.UserDTO;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultUserService;
import com.nhnacademy.jdbc.board.controller.LoginController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
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
    void loginSuccessTest() throws Exception {
        UserDTO user = new UserDTO("user", "123");
        user.setUserNum(1);
        when(userService.successLogin(anyString(), anyString())).thenReturn(true);

        MvcResult mvcResult = mockMvc.perform(post("/login")
                                         .param("userNum", String.valueOf(user.getUserNum()))
                                         .param("id", user.getId())
                                         .param("pw", user.getPw()))
                                     .andExpect(view().name("index/index"))
                                     .andReturn();

        assertThat(mvcResult.getRequest().getSession(false).getAttribute("id")).isEqualTo("user");
        assertThat(mvcResult.getModelAndView().getModel().get("user")).isEqualTo("user");
    }

    @Test
    void loginFailedTest() throws Exception {
        UserDTO user = new UserDTO("user", "wrong");
        user.setUserNum(1);
        when(userService.successLogin(anyString(), anyString())).thenReturn(false);

        MvcResult mvcResult = mockMvc.perform(post("/login")
                                         .param("userNum", String.valueOf(user.getUserNum()))
                                         .param("id", user.getId())
                                         .param("pw", user.getPw()))
                                     .andExpect(view().name("redirect:/login"))
                                     .andReturn();

        assertThat(mvcResult.getRequest().getSession(false)).isNull();
    }

    @Test
    void loginCheckTestSessionIsNull() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/login"))
            .andExpect(view().name("login/loginForm"))
            .andReturn();

        assertThat(mvcResult.getModelAndView().getModel().get("user")).isEqualTo("Guest");
    }

    @Test
    void loginCheckTestSessionisNonNull() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "user");

        MvcResult mvcResult = mockMvc.perform(get("/login").session(session))
            .andExpect(view().name("index/index"))
            .andReturn();

        assertThat(mvcResult.getRequest().getSession(false).getAttribute("id")).isEqualTo("user");
        assertThat(mvcResult.getModelAndView().getModel().get("user")).isEqualTo("user");
    }

    @Test
    void logoutTest() throws Exception {
        MockHttpSession session = new MockHttpSession();

        MvcResult mvcResult = mockMvc.perform(get("/logout").session(session))
            .andExpect(view().name("index/index"))
            .andReturn();

        assertThat(mvcResult.getRequest().getSession(false)).isNull();
        assertThat(mvcResult.getModelAndView().getModel().get("user")).isEqualTo("Guest");
    }
}
