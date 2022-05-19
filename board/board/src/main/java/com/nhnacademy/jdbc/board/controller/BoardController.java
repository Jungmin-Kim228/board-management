package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.compre.domain.Post;
import com.nhnacademy.jdbc.board.compre.service.PostService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultPostService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    private final PostService postService;

    public BoardController(DefaultPostService postService) {
        this.postService = postService;
    }

    @GetMapping("/board")
    public String boardView(Model model) {
        List<Post> posts = postService.getPosts();
        model.addAttribute("allPost", posts);
        return "board/boardView";
    }

    @GetMapping("/boardRegister")
    public String boardRegister() {
        return "board/boardRegister";
    }

    @GetMapping("/boardModify")
    public String boardModify() {
        return "board/boardModify";
    }
}
