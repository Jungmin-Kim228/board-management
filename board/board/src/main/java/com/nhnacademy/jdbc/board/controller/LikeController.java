package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.compre.service.LikeService;
import com.nhnacademy.jdbc.board.compre.service.UserService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultLikeService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultUserService;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LikeController {
    private final LikeService likeService;
    private final UserService userService;

    public LikeController(DefaultLikeService likeService, DefaultUserService userService) {
        this.likeService = likeService;
        this.userService = userService;
    }

    @GetMapping("/boardLikes")
    public String likesView(HttpServletRequest req) {
        return null;
    }

    @PostMapping("/like/{post_no}")
    public String insertOrDeleteLike(@PathVariable("post_no") int id,
                                     @RequestParam("button") String like,
                                     HttpServletRequest req) {
        if (like.equals("like")) {
            likeService.likeInsert(id, userService.getUser(
                String.valueOf(req.getSession(false).getAttribute("id"))));
        }
        if(!like.equals("like")){
            likeService.likeDelete(id, userService.getUser(
                String.valueOf(req.getSession(false).getAttribute("id"))));
        }
        return "redirect:/board";
    }
}
