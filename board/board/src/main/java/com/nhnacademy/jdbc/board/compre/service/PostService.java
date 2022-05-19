package com.nhnacademy.jdbc.board.compre.service;

import com.nhnacademy.jdbc.board.compre.domain.Post;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

public interface PostService {
    Optional<Post> getPost(int id);

    List<Post> getPosts();

    void register(Post post, int num);

    void update(int id, Post post);

    void delete(int id);
}
