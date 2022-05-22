package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.compre.dto.UserDTO;
import com.nhnacademy.jdbc.board.compre.exception.ValidationFailedException;
import com.nhnacademy.jdbc.board.compre.service.UserService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultUserService;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String login(@ModelAttribute @Valid UserDTO user,
                        BindingResult bindingResult,
                        HttpServletRequest req,
                        Model model) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        if (userService.successLogin(user.getId(), user.getPw())) {
            req.getSession(true);
            req.getSession().setAttribute("id", user.getId());
            model.addAttribute("user", user.getId());
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
