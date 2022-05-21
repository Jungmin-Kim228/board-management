package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.compre.domain.Pagination;
import com.nhnacademy.jdbc.board.compre.dto.PostDTO;
import com.nhnacademy.jdbc.board.compre.dto.ViewPostDTO;
import com.nhnacademy.jdbc.board.compre.service.LikeService;
import com.nhnacademy.jdbc.board.compre.service.PostService;
import com.nhnacademy.jdbc.board.compre.service.UserService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultPostService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultUserService;
import java.io.IOException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Slf4j
@Controller
public class BoardController {
    private final PostService postService;
    private final UserService userService;
    private final LikeService likeService;


    public BoardController(DefaultPostService postService, DefaultUserService userService,
                           LikeService likeService) {
        this.postService = postService;
        this.userService = userService;
        this.likeService = likeService;
    }

    @GetMapping("/board")
    public String boardView(Model model, @RequestParam(value = "page", defaultValue = "1") final int page, HttpServletRequest req) {
        Pagination pagination = new Pagination(postService.getCount(), page);
        List<PostDTO> list = postService.getListPage(pagination);
        List<PostDTO> postDTOS = new ArrayList<>();

        for (PostDTO postDTO : list) {
            if(!postDTO.isCheckHide()) {
                Optional<HttpSession> session = Optional.ofNullable(req.getSession(false));
                checkUserLiked(session, postDTO);
                postDTOS.add(postDTO);
            }
        }
        model.addAttribute("allPost", postDTOS);
        model.addAttribute("page", page);
        model.addAttribute("pagination", pagination);
        return "board/boardView";
    }

    private void checkUserLiked(Optional<HttpSession> session, PostDTO dto) {
        session.ifPresent(httpSession -> {
            Object userId = httpSession.getAttribute("id");
            if(Objects.nonNull(userId)){
                dto.setLike(likeService.userLike(dto.getId(), userId.toString()));
                return;
            }
            dto.setLike(false);
        });
    }

    @GetMapping("/boardRegister")
    public String readyBoardRegister() {
        return "board/boardRegisterForm";
    }

    @PostMapping("/boardRegister")
    public String boardRegister(@RequestParam("writeTitle") String title,
                                @RequestParam("writeContent") String content,
                                @RequestParam("file") MultipartFile file,
                                MultipartHttpServletRequest req) throws IOException {
        if (Objects.nonNull(file)) {
            String filename = file.getOriginalFilename().split("\\\\")[file.getOriginalFilename().split("\\\\").length-1];
            Integer user = userService.getUser(
                (String) req.getSession(false).getAttribute("id"));
            postService.register(new PostDTO(title, content,
                new Timestamp(new Date().getTime()), file.getBytes(), filename), user);
        } else {
            Integer user = userService.getUser(
                (String) req.getSession(false).getAttribute("id"));
            postService.register(new PostDTO(title, content,
                new Timestamp(new Date().getTime())), user);
        }
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
        postService.update(postNo, title, content, new Timestamp(new Date().getTime()));
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
            List<ViewPostDTO> postDTOS = postService.getPosts();
            postDTOS.removeIf(post -> !post.isCheckHide());
            model.addAttribute("recoverPost", postDTOS);
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
