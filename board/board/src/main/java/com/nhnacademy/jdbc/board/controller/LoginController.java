package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.compre.service.UserService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultUserService;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String loginCheck(HttpServletRequest req,
                             Model model) {
        if (Objects.nonNull(req.getSession(false))) {
            String user = (String) req.getSession(false).getAttribute("id");
            model.addAttribute("user", user);
        } else {
            model.addAttribute("user","Guest");
            return "login/loginForm";
        }
        return "index/index";
    }

    @PostMapping("/login")
    public String login(@RequestParam("logId") String id,
                        @RequestParam("logPw") String pw,
                        HttpServletRequest req,
                        Model model) {
        if (userService.successLogin(id, pw)) {
            req.getSession(true);
            req.getSession().setAttribute("id", id);
            model.addAttribute("user", id);
            return "index/index";
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req,
                         Model model) {
        req.getSession(false).invalidate();
        model.addAttribute("user","Guest");
        return "index/index";
    }
}
