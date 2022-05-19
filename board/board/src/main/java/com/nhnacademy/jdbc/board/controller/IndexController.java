package com.nhnacademy.jdbc.board.controller;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
@Slf4j
public class IndexController {
    @GetMapping(value = {"/","/index.nhn"})
    public String index(HttpServletRequest req,
                        Model model) {
        if (Objects.nonNull(req.getSession(false))) {
            String user = (String) req.getSession(false).getAttribute("id");
            model.addAttribute("user", user);
        } else {
            model.addAttribute("user","Guest");
        }
        return "index/index";
    }
}
