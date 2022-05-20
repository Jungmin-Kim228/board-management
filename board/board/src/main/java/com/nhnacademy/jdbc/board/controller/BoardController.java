package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.compre.domain.Pagination;
import com.nhnacademy.jdbc.board.compre.domain.Post;
import com.nhnacademy.jdbc.board.compre.service.PostService;
import com.nhnacademy.jdbc.board.compre.service.UserService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultPostService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultUserService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String boardView(Model model, @RequestParam(value = "page", defaultValue = "1") final int page) {
        Pagination pagination = new Pagination(postService.getCount(), page);
        List<Post> posts = postService.getListPage(pagination);

        posts.removeIf(Post::isCheckHide);

        model.addAttribute("allPost", posts);
        model.addAttribute("page", page);
        model.addAttribute("pagination", pagination);
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
        Integer user = userService.getUser(
            (String)req.getSession(false).getAttribute("id"));
        postService.register(new Post(title, content, new Timestamp(new Date().getTime())), user);
        return "redirect:/board";
    }

    @GetMapping("/boardModify/{postNo}")
    public String readyBoardModify(@PathVariable("postNo") int postNo,
                                   HttpServletRequest req,
                                   Model model) {
        if(req.getSession().getAttribute("id").equals(postService.getPost(postNo).get().getWriter())) {
            model.addAttribute("postNo", postNo);
            return "board/boardModifyForm";
        }
        return "redirect:/content?id=" + postNo;
    }

    @PostMapping("/boardModify/{postNo}")
    public String boardModify(@PathVariable("postNo") int postNo,
                              @RequestParam("modifyTitle") String title,
                              @RequestParam("modifyContent") String content) {
        postService.update(postNo, title, content);
        return "redirect:/board";
    }

    @PostMapping("/boardDelete/{postNo}")
    public String boardDelete(@PathVariable("postNo") int postNo,
                              HttpServletRequest req) {
        if((req.getSession().getAttribute("id").equals(postService.getPost(postNo).get().getWriter()))
        || (userService.checkAdmin(userService.getUser((String)req.getSession(false).getAttribute("id"))))){
            postService.delete(postNo);
            return "redirect:/board";
        }
        return "redirect:/content?id=" + postNo;
    }

    @GetMapping("/boardRecover")
    public String recoverBoardView(HttpServletRequest req,
                                   Model model) {
        if(userService.checkAdmin(userService.getUser((String)req.getSession(false).getAttribute("id")))) {
            List<Post> posts = postService.getPosts();
            posts.removeIf(post -> !post.isCheckHide());
            model.addAttribute("recoverPost", posts);
            return "board/boardRecover";
        }
        return "redirect:/board";
    }

    @PostMapping("/boardRecover/{postNo}")
    public String boardRecover(@PathVariable("postNo") int id) {
        postService.recover(id);
        return "redirect:/boardRecover";
    }
}
