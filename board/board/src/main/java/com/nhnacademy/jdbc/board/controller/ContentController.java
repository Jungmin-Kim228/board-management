package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.compre.domain.Comment;
import com.nhnacademy.jdbc.board.compre.domain.Post;
import com.nhnacademy.jdbc.board.compre.service.CommentService;
import com.nhnacademy.jdbc.board.compre.service.PostService;
import com.nhnacademy.jdbc.board.compre.service.UserService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultCommentService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultPostService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultUserService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContentController {
    private final UserService userService;
    private final CommentService commentService;
    private final PostService postService;

    public ContentController(DefaultCommentService commentService, DefaultUserService userService, DefaultPostService postService) {
        this.commentService = commentService;
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/content")
    public String readyBoardContent(@RequestParam("id") int id,
                                    Model model) {
        Post post = postService.getPost(id).get();
        List<Comment> comment = commentService.getComments(id);
        model.addAttribute("post", post);
        model.addAttribute("comment", comment);
        return "content/boardContent";
    }
}
