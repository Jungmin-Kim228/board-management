package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.compre.domain.Post;
import com.nhnacademy.jdbc.board.compre.domain.User;
import com.nhnacademy.jdbc.board.compre.service.PostService;
import com.nhnacademy.jdbc.board.compre.service.UserService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultPostService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultUserService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class BoardController {
    private final PostService postService;
    private final UserService userService;

    public BoardController(DefaultPostService postService, DefaultUserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/board")
    public String boardView(Model model) {
        List<Post> posts = postService.getPosts();
        model.addAttribute("allPost", posts);
        return "board/boardView";
    }

    @GetMapping("/boardRegister")
    public String readyBoardRegister() {
        return "board/boardRegisterForm";
    }

    @PostMapping("/boardRegister")
    public String boardRegister(@RequestParam("writeTitle") String title,
                                @RequestParam("writeContent") String content,
                                HttpServletRequest req) {
        Optional<User> user = userService.getUser(
            (String)req.getSession(false).getAttribute("id"));
        postService.register(new Post(title, content, new Timestamp(new Date().getTime())),user.get().getUserNum());
        return "redirect:/board";
    }

    @GetMapping("/boardModify")
    public String readyBoardModify() {
        return "board/boardModifyForm";
    }

    @PostMapping("/boardModify")
    public String BoardModify() {
        return "redirect:/board";
    }
}
