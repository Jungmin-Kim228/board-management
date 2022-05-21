package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.compre.dto.PostDTO;
import com.nhnacademy.jdbc.board.compre.service.LikeService;
import com.nhnacademy.jdbc.board.compre.service.PostService;
import com.nhnacademy.jdbc.board.compre.service.UserService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultLikeService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultPostService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultUserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    private final PostService postService;
    private final UserService userService;
    private final LikeService likeService;

    public SearchController(DefaultPostService postService, DefaultUserService userService,
                            DefaultLikeService likeService) {
        this.postService = postService;
        this.userService = userService;
        this.likeService = likeService;
    }

    @PostMapping("/searchBoard")
    public String searchBoard(@RequestParam("searchTitle") String title,
                              HttpServletRequest req,
                              Model model) {
        List<PostDTO> list = postService.searchPost(title);
        List<PostDTO> postDTOS = new ArrayList<>();
        for (PostDTO postDTO : list) {
            if(!postDTO.isCheckHide()) {
                if(Objects.isNull(req.getSession(false))) {
                    postDTO.setLike(false);
                } else {
                    if (likeService.userLike(postDTO.getId(),
                        String.valueOf(req.getSession(false).getAttribute("id")))) {
                        postDTO.setLike(true);
                    }
                }
                postDTOS.add(postDTO);
            }
        }
        model.addAttribute("searchPosts", postDTOS);
        return "search/searchView";
    }
}
