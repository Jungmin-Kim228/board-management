package com.nhnacademy.jdbc.board.compre.service.impl;

import com.nhnacademy.jdbc.board.compre.domain.Post;
import com.nhnacademy.jdbc.board.compre.domain.PostMainView;
import com.nhnacademy.jdbc.board.compre.dto.CommentDTO;
import com.nhnacademy.jdbc.board.compre.domain.Pagination;
import com.nhnacademy.jdbc.board.compre.dto.PostDTO;
import com.nhnacademy.jdbc.board.compre.dto.ViewPostDTO;
import com.nhnacademy.jdbc.board.compre.mapper.PostMapper;
import com.nhnacademy.jdbc.board.compre.service.CommentService;
import com.nhnacademy.jdbc.board.compre.service.PostService;
import com.nhnacademy.jdbc.board.compre.service.UserService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultPostService implements PostService {
    private final PostMapper postMapper;
    private final UserService userService;
    private final CommentService commentService;

    public DefaultPostService(PostMapper postMapper, DefaultUserService userService, DefaultCommentService commentService) {
        this.postMapper = postMapper;
        this.userService = userService;
        this.commentService = commentService;
    }

    @Override
    public Optional<PostDTO> getPost(int id) {
        if (Objects.isNull(postMapper.selectPost(id))) {
            return Optional.empty();
        }
        Post pod = postMapper.selectPost(id).get();
        return Optional.of(new PostDTO(pod.getPostNo(),
            pod.getPostTitle(), (userService.getUserId(pod.getUserNo())),
            pod.getPostContent(), pod.getPostWriteDatetime(), pod.getPostModifyDatetime()
            ,pod.isPostCheckHide(),pod.getFilename(), pod.getFile(), pod.getParent(), pod.getDepth()));
    }



    @Override
    public List<ViewPostDTO> getPosts() { // 지울 것
        List<PostMainView> postDTO = postMapper.selectPosts();
        List<ViewPostDTO> postDTOS = new ArrayList<>();
        for (PostMainView postDto : postDTO) {
            List<CommentDTO> commentDTO = commentService.getComments(postDto.getPostNo());
                postDTOS.add(new ViewPostDTO(postDto.getPostNo(),
                    postDto.getPostTitle(), postDto.getUserId()
                    , postDto.getPostWriteDatetime(), postDto.getPostModifyDatetime(),
                    commentDTO.size(),
                    postDto.isPostCheckHide(), postDto.getParent(), postDto.getDepth()));
        }
        return postDTOS;
    }

    @Override
    public void register(PostDTO postDTO, int num) {
        postMapper.postRegister(postDTO, num);
    }

    @Override
    public void update(int id, String title, String content, Date date) {
        postMapper.postUpdate(id, title, content, date);
    }

    @Override
    public void delete(int id) {
        postMapper.postDelete(id);
        postMapper.repostDelete(id);
    }

    @Override
    public void recover(int id) {
        postMapper.postRecover(id);
    }

    @Override
    public int getCount() {
        return this.postMapper.postCount();
    }

    @Override
    public List<PostDTO> getListPage(final Pagination pagination) {
        List<PostMainView> postDtoList = postMapper.getListPage(pagination);
        List<PostDTO> postDTOS = new ArrayList<>();
        List<Integer> no = new ArrayList<>();
        for (PostMainView postDto : postDtoList) {
            List<CommentDTO> commentDTO = commentService.getComments(postDto.getPostNo());
            if (postDto.getDepth() != 0) {
                int depthCount = checkCount(postDTOS, postDto.getParent());
                postDTOS.add(no.indexOf(postDto.getParent()) + depthCount,
                    new PostDTO(postDto.getPostNo(),
                    postDto.getPostTitle(), postDto.getUserId(),
                    "", postDto.getPostWriteDatetime(), postDto.getPostModifyDatetime(),
                    commentDTO.size(), postDto.isPostCheckHide(), postDto.getParent(),
                    postDto.getDepth()
                ));
                no.add(no.indexOf(postDto.getParent()) + depthCount,postDto.getPostNo());
            } else {
                postDTOS.add(new PostDTO(postDto.getPostNo(),
                    postDto.getPostTitle(), postDto.getUserId(),
                    "", postDto.getPostWriteDatetime(), postDto.getPostModifyDatetime(),
                    commentDTO.size(), postDto.isPostCheckHide(), postDto.getParent(),
                    postDto.getDepth()
                ));
                no.add(postDto.getPostNo());
            }
        }
        return postDTOS;
    }

    private int checkCount(List<PostDTO> postDTOS, int p) {
        int result = 1;
        for(PostDTO postDTO : postDTOS) {
            if(postDTO.getParent() == p) {
                result++;
            }
        }
        return result;
    }

    @Override
    public List<ViewPostDTO> searchPost(String title) {
        List<PostMainView> postDTO = postMapper.searchPost(title);
        List<ViewPostDTO> postDTOS = new ArrayList<>();
        for (PostMainView postDto : postDTO) {
            List<CommentDTO> commentDTO = commentService.getComments(postDto.getPostNo());
            postDTOS.add(new ViewPostDTO(postDto.getPostNo(),

                postDto.getPostTitle(), postDto.getUserId(),
                postDto.getPostWriteDatetime(), postDto.getPostModifyDatetime(),
                commentDTO.size(),
                postDto.isPostCheckHide(),postDto.getParent(), postDto.getDepth()));
        }
        return postDTOS;
    }
}
