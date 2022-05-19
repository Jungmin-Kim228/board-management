package com.nhnacademy.jdbc.board.compre.service;

import com.nhnacademy.jdbc.board.compre.domain.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> getComment(int id);

    List<Comment> getComments(int id);

    void register(Comment comment);

    void update(int id, Comment comment);

    void delete(int id);
}
