package com.nhnacademy.jdbc.board.compre.mapper;

import com.nhnacademy.jdbc.board.compre.domain.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentMapper {

    Optional<Comment> selectComment(int id);

    List<Comment> selectComments();

    void commentRegister(Comment comment);

    void commentUpdate(int id, Comment comment);

    void commentDelete(int id);
}
