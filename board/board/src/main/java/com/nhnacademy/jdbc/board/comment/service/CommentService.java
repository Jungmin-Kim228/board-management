package com.nhnacademy.jdbc.board.comment.service;

import com.nhnacademy.jdbc.board.comment.domain.Comment;
import com.nhnacademy.jdbc.board.post.domain.Post;
import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> getComment(int id);

    List<Comment> getComments();

    void register(Comment comment);

    void update(int id, Comment comment);

    void delete(int id);
}
