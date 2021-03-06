package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.compre.dto.ViewPostDTO;
import com.nhnacademy.jdbc.board.compre.service.LikeService;
import com.nhnacademy.jdbc.board.compre.service.PostService;
import com.nhnacademy.jdbc.board.compre.service.ViewService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultLikeService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultPostService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultViewService;
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
    private final LikeService likeService;

    private final ViewService viewService;

    public SearchController(DefaultPostService postService, DefaultLikeService likeService,
                            DefaultViewService viewService) {
        this.postService = postService;
        this.likeService = likeService;
        this.viewService = viewService;
    }

    @PostMapping("/searchBoard")
    public String searchBoard(@RequestParam("searchTitle") String title,
                              HttpServletRequest req,
                              Model model) {
        List<ViewPostDTO> list = postService.searchPost(title);
        List<ViewPostDTO> postDTOS = new ArrayList<>();
        for (ViewPostDTO postDTO : list) {
            if (!postDTO.isCheckHide()) {
                if (Objects.isNull(req.getSession(false))) {
                    postDTO.setLike(false);
                } else {
                    if (likeService.userLike(postDTO.getId(),
                        String.valueOf(req.getSession(false).getAttribute("id")))) {
                        postDTO.setLike(true);
                    }
                }
                postDTO.setViewCount(viewService.findView(postDTO.getId()).size());
                postDTOS.add(postDTO);
            }
        }
        model.addAttribute("searchPosts", postDTOS);
        return "search/searchView";
    }
}
