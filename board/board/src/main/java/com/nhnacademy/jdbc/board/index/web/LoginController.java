package com.nhnacademy.jdbc.board.index.web;

import com.nhnacademy.jdbc.board.compre.service.UserService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultUserService;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final UserService userService;

    public LoginController(DefaultUserService userService) {
        this.userService = userService;
    }
    @GetMapping("/login")
    public String loginCheck(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if(Objects.isNull(session)) {
            return "login/loginForm";
        }
        return "index/index";
    }

    @PostMapping("/login")
    public String login(@RequestParam("logId") String id,
                        @RequestParam("logPw") String pw,
                        HttpServletRequest req) {
        if (userService.successLogin(id, pw)) {
            req.getSession(true);
            req.getSession().setAttribute("id", id);
            return "index/index";
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req) {
        req.getSession(false).invalidate();
        return "index/index";
    }
}
