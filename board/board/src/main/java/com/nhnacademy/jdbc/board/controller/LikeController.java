package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.compre.dto.PostDTO;
import com.nhnacademy.jdbc.board.compre.dto.ViewPostDTO;
import com.nhnacademy.jdbc.board.compre.service.LikeService;
import com.nhnacademy.jdbc.board.compre.service.PostService;
import com.nhnacademy.jdbc.board.compre.service.UserService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultLikeService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultPostService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultUserService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LikeController {
    private final LikeService likeService;
    private final PostService postService;
    private final UserService userService;

    public LikeController(DefaultLikeService likeService, DefaultPostService postService, DefaultUserService userService) {
        this.likeService = likeService;
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/boardLikes")
    public String likesView(HttpServletRequest req,
                            Model model) {
        List<ViewPostDTO> list = new ArrayList<>();
        List<ViewPostDTO> postDTOS = postService.getPosts();
        for (ViewPostDTO postDTO : postDTOS) {
            if (likeService.userLike(postDTO.getId(),
                (String) req.getSession(false).getAttribute("id"))) {
                if(!postDTO.isCheckHide()) {
                    list.add(postDTO);
                }
            }
        }
        model.addAttribute("likePost", list);
        return "like/likeView";
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
