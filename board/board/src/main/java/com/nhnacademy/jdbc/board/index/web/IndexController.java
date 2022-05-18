package com.nhnacademy.jdbc.board.index.web;

import com.nhnacademy.jdbc.board.user.domain.User;
import com.nhnacademy.jdbc.board.user.service.UserService;
import com.nhnacademy.jdbc.board.user.service.impl.DefaultUserService;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
@Slf4j
public class IndexController {
    private final UserService userService;

    public IndexController(DefaultUserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/","/index.nhn"})
    public String index(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if(Objects.isNull(session)) {
            return"login/loginForm";
        }
        return "index/index";
    }

    @PostMapping("/")
    public String login(@RequestParam("logId") String id,
                        @RequestParam("logPw") String pw,
                        HttpServletRequest req) {
        if (userService.successLogin(id, pw)) {
            req.getSession().setAttribute("id", id);
            return "redirect:/";
        }
        return "login/loginForm";
    }
}
