package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.compre.domain.Comment;
import com.nhnacademy.jdbc.board.compre.dto.CommentDTO;
import com.nhnacademy.jdbc.board.compre.dto.PostDTO;
import com.nhnacademy.jdbc.board.compre.service.CommentService;
import com.nhnacademy.jdbc.board.compre.service.PostService;
import com.nhnacademy.jdbc.board.compre.service.UserService;
import com.nhnacademy.jdbc.board.compre.service.ViewService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultCommentService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultPostService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultUserService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultViewService;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class ContentController {
    private final UserService userService;
    private final CommentService commentService;
    private final PostService postService;
    private final ViewService viewService;

    public ContentController(DefaultCommentService commentService, DefaultUserService userService,
                             DefaultPostService postService, DefaultViewService viewService) {
        this.commentService = commentService;
        this.userService = userService;
        this.postService = postService;
        this.viewService = viewService;
    }

    @GetMapping("/content")
    public String readyBoardContent(@RequestParam("id") int id, HttpServletRequest req,
                                    Model model) {
        if (Objects.nonNull(req.getSession(false)) &&
            !viewService.isView(id, String.valueOf(req.getSession(false).getAttribute("id")))) {
            viewService.insertView(id,
                userService.getUser(String.valueOf(req.getSession(false).getAttribute("id"))));
        }
        PostDTO postDTO = postService.getPost(id).get();
        List<CommentDTO> commentDTO = commentService.getComments(id);
        model.addAttribute("post", postDTO);
        model.addAttribute("comment", commentDTO);
        return "content/boardContent";
    }

    @PostMapping("/commentRegister")
    public String commentRegister(@RequestParam("id") int id,
                                  @RequestParam("comment") String comment,
                                  HttpServletRequest req) {
        commentService.register(id,
            userService.getUser((String) req.getSession(false).getAttribute("id")), comment);
        return "redirect:/content?id=" + id;
    }

    @PostMapping("/comment/{commentNo}")
    public String commentModifyOrDelete(@PathVariable int commentNo,
                                        @RequestParam("button") String button,
                                        HttpServletRequest req,
                                        Model model) {
        Comment com = commentService.getComment(commentNo).get();
        CommentDTO commentDTO = new CommentDTO(com.getCommentNo(),
            userService.getUserId(com.getUserNo()), com.getCommentContent());
        if ((commentDTO.getCommentWriter().equals(req.getSession(false).getAttribute("id")))) {
            if (button.equals("Modify")) {
                model.addAttribute("modifyComment", commentDTO);
                return "comment/commentModify";
            } else {
                commentService.delete(commentNo);
                return "redirect:/content?id=" + com.getPostNo();
            }
        }
        return "redirect:/content?id=" + com.getPostNo();
    }

    @PostMapping("/commentModify/{commentNo}")
    public String commentModify(@PathVariable int commentNo,
                                @RequestParam("commentContent") String content) {
        commentService.update(commentNo, content);
        Comment com = commentService.getComment(commentNo).get();
        return "redirect:/content?id=" + com.getPostNo();
    }

}
