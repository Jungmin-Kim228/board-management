package com.nhnacademy.jdbc.board.post.service.impl;

import com.nhnacademy.jdbc.board.post.domain.Post;
import com.nhnacademy.jdbc.board.post.mapper.PostMapper;
import com.nhnacademy.jdbc.board.post.service.PostService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DefaultPostService implements PostService {
    private final PostMapper postMapper;

    public DefaultPostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    public Optional<Post> getPost(int id) {
        if (Objects.isNull(postMapper.selectPost(id))) {
            return Optional.empty();
        }
        return postMapper.selectPost(id);
    }

    @Override
    public List<Post> getPosts() {
        return postMapper.selectPosts();
    }

    @Override
    public void register(Post post) {
        postMapper.postRegister(post);
    }

    @Override
    public void update(int id, Post post) {
        postMapper.postUpdate(id, post);
    }

    @Override
    public void delete(int id) {
        postMapper.postDelete(id);
    }
}
