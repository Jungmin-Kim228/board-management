package com.nhnacademy.jdbc.board.post.service;

import com.nhnacademy.jdbc.board.post.domain.Post;
import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<Post> getPost(int id);

    List<Post> getPosts();

    void register(Post post);

    void update(int id, Post post);

    void delete(int id);
}
