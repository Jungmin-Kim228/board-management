package com.nhnacademy.jdbc.board.index.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequestMapping
@Slf4j
public class IndexController {
    @GetMapping(value = {"/","/index.nhn"})
    public String index(HttpServletRequest req) {
        return "index/index";
    }
}
