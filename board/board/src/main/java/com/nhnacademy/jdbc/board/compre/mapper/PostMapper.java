package com.nhnacademy.jdbc.board.compre.mapper;

import com.nhnacademy.jdbc.board.compre.domain.Post;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface PostMapper {

    @Select("SELECT * FROM Posts WHERE id = #{id}")
    Optional<Post> selectPost(int id);

    @Select("SELECT P.post_no AS id,\n" +
        "        P.post_title AS title,\n" +
        "        U.user_id AS writer,\n" +
        "        P.post_write_datetime AS writeDate,\n" +
        "        COUNT(C.comment_no) AS commentCount\n" +
        "        FROM Posts AS P\n" +
        "        INNER JOIN Users AS U\n" +
        "        ON P.user_no = U.user_no\n" +
        "        INNER JOIN Comments AS C\n" +
        "        ON P.post_no = C.post_no\n" +
        "        GROUP BY P.post_no")
    @ResultType(Post.class)
    List<Post> selectPosts();

    @Insert("INSERT INTO Posts(user_no, post_title, post_content, post_write_datetime, post_check_hide, post_hits)" +
        " VALUES (#{num}, '${Post.title}', ${Post.content}, ${Post.writeDate}, 0, 0)")
    void postRegister(@Param("Post") Post post, @Param("num") int num);

    @Update("UPDATE Posts SET name = '${name}' WHERE id = #{id}")
    void postUpdate(int id, Post post);

    @Delete("DELETE FROM Posts WHERE id = #{id}")
    void postDelete(int id);
}
