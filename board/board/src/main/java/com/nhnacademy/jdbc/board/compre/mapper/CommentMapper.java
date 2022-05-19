package com.nhnacademy.jdbc.board.compre.mapper;

import com.nhnacademy.jdbc.board.compre.domain.Comment;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CommentMapper {

    @Select("SELECT * FROM Comments WHERE id = #{id}")
    Optional<Comment> selectComment(int id);

    @Select("SELECT * FROM Comments")
    List<Comment> selectComments();

    @Insert("INSERT INTO Comments(id, name, created_at) VALUES (#{id}, '${name}, #{created_at}")
    void commentRegister(Comment comment);

    @Update("UPDATE Comments SET name='${name}' WHERE id = #{id}")
    void commentUpdate(int id, Comment comment);

    @Delete("DELETE FROM Comments WHERE id = #{id}")
    void commentDelete(int id);
}
