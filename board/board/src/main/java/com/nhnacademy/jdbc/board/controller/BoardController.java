package com.nhnacademy.jdbc.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/board")
    public String boardView() {
        return "board/boardView";
    }
}
