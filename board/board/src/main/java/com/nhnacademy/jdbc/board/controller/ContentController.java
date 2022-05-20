package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.compre.dto.CommentDTO;
import com.nhnacademy.jdbc.board.compre.domain.Comment;
import com.nhnacademy.jdbc.board.compre.domain.Post;
import com.nhnacademy.jdbc.board.compre.service.CommentService;
import com.nhnacademy.jdbc.board.compre.service.PostService;
import com.nhnacademy.jdbc.board.compre.service.UserService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultCommentService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultPostService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultUserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/commentRegister")
    public String commentRegister(@RequestParam("id") int id,
                                  @RequestParam("comment") String comment,
                                  HttpServletRequest req) {
        commentService.register(id,userService.getUser((String)req.getSession(false).getAttribute("id")) ,comment);
        return "redirect:/content?id=" + id;
    }

    @PostMapping("/comment/{commentNo}")
    public String commentModifyOrDelete(@PathVariable int commentNo,
                                        @RequestParam("button") String button,
                                        HttpServletRequest req,
                                        Model model) {
        CommentDTO com = commentService.getComment(commentNo).get();
        Comment comment = new Comment(com.getCommentNo(),
            userService.getUserId(com.getUserNo()), com.getCommentContent());
        if((comment.getCommentWriter().equals(req.getSession(false).getAttribute("id"))) ||
            (userService.checkAdmin(userService.getUser((String)req.getSession(false).getAttribute("id"))))) {
            if (button.equals("Modify")) {
                model.addAttribute("modifyComment", comment);
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
        CommentDTO com = commentService.getComment(commentNo).get();
        return "redirect:/content?id=" + com.getPostNo();
    }

}
