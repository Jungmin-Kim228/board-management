package com.nhnacademy.jdbc.board.compre.mapper;

import com.nhnacademy.jdbc.board.compre.domain.Comment;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface CommentMapper {

    Optional<Comment> selectComment(@Param("id") int id);

    List<Comment> selectComments(@Param("id") int id);

    void commentRegister(@Param("postNo") int postNo, @Param("userNo") int userNo, @Param("comment") String comment);

    void commentUpdate(@Param("id") int id, @Param("content") String content);

    void commentDelete(@Param("id") int id);
}
