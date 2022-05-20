package com.nhnacademy.jdbc.board.compre.service.impl;

import com.nhnacademy.jdbc.board.compre.dto.PostDTO;
import com.nhnacademy.jdbc.board.compre.domain.Comment;
import com.nhnacademy.jdbc.board.compre.domain.Post;
import com.nhnacademy.jdbc.board.compre.mapper.PostMapper;
import com.nhnacademy.jdbc.board.compre.service.CommentService;
import com.nhnacademy.jdbc.board.compre.service.PostService;
import com.nhnacademy.jdbc.board.compre.service.UserService;
import java.util.ArrayList;
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
    public Optional<Post> getPost(int id) {
        if (Objects.isNull(postMapper.selectPost(id))) {
            return Optional.empty();
        }
        PostDTO pod = postMapper.selectPost(id).get();
        return Optional.of(new Post(pod.getPostNo(),
            pod.getPostTitle(), (userService.getUserId(pod.getUserNo())),
            pod.getPostContent(), pod.getPostWriteDatetime(), pod.getPostHits(), pod.isPostCheckHide()));
    }

    @Override
    public List<Post> getPosts() {
        List<PostDTO> postDTO = postMapper.selectPosts();
        List<Post> posts = new ArrayList<>();
        for (PostDTO postDto : postDTO) {
            List<Comment> comment = commentService.getComments(postDto.getPostNo());
                posts.add(new Post(postDto.getPostNo(),
                    postDto.getPostTitle(), (userService.getUserId(postDto.getUserNo())),
                    postDto.getPostContent(), postDto.getPostWriteDatetime(), comment.size(),
                    postDto.isPostCheckHide()));
        }
        return posts;
    }

    @Override
    public void register(Post post, int num) {
        postMapper.postRegister(post, num);
    }

    @Override
    public void update(int id, String title, String content) {
        postMapper.postUpdate(id, title, content);
    }

    @Override
    public void delete(int id) {
        postMapper.postDelete(id);
    }

    @Override
    public void recover(int id) {
        postMapper.postRecover(id);
    }
}
