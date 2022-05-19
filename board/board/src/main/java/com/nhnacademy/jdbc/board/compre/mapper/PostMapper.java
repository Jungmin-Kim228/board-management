package com.nhnacademy.jdbc.board.compre.mapper;

import com.nhnacademy.jdbc.board.compre.domain.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface PostMapper {

    Optional<Post> selectPost(int id);

    List<Post> selectPosts();

    void postRegister(Post post);

    void postUpdate(int id, Post post);

    void postDelete(int id);
}
