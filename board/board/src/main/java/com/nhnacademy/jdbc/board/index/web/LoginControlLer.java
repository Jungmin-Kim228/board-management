package com.nhnacademy.jdbc.board.index.web;

import com.nhnacademy.jdbc.board.user.service.UserService;
import com.nhnacademy.jdbc.board.user.service.impl.DefaultUserService;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginControlLer {
    private final UserService userService;

    public LoginControlLer(DefaultUserService userService) {
        this.userService = userService;
    }
    @GetMapping("/login")
    public String loginCheck(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if(Objects.isNull(session)) {
            return "login/loginForm";
        }
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@RequestParam("logId") String id,
                        @RequestParam("logPw") String pw,
                        HttpServletRequest req) {
        if (userService.successLogin(id, pw)) {
            req.getSession().setAttribute("id", id);
            return "redirect:/";
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req) {
        req.getSession().invalidate();
        return "redirect:/";
    }
}
