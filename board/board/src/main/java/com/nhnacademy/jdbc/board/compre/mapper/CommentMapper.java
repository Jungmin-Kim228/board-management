package com.nhnacademy.jdbc.board.compre.mapper;

import com.nhnacademy.jdbc.board.compre.dao.CommentDAO;
import com.nhnacademy.jdbc.board.compre.domain.Comment;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface CommentMapper {

    Optional<Comment> selectComment(int id);

    List<CommentDAO> selectComments(@Param("id") int id);

    void commentRegister(Comment comment);

    void commentUpdate(int id, Comment comment);

    void commentDelete(int id);
}
