package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.compre.service.CommentService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultCommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContentController {
    private final CommentService commentService;

    public ContentController(DefaultCommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/content")
    public String readyBoardContent(@RequestParam("id") int id,
                                    Model model) {
        model.addAttribute("post", id);
        return "content/boardContent";
    }
}
