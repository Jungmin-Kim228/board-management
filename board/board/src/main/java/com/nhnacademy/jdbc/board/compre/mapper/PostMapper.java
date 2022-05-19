package com.nhnacademy.jdbc.board.compre.mapper;

import com.nhnacademy.jdbc.board.compre.domain.Post;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface PostMapper {

    @Select("SELECT * FROM Posts WHERE id = #{id}")
    Optional<Post> selectPost(int id);

    @Select("SELECT * from Posts")
    List<Post> selectPosts();

    @Insert("INSERT INTO Posts(id, name, created_at) VALUES (#{id}, '${name}', #{createdAt})")
    void postRegister(Post post);

    @Update("UPDATE Posts SET name = '${name}' WHERE id = #{id}")
    void postUpdate(int id, Post post);

    @Delete("DELETE FROM Posts WHERE id = #{id}")
    void postDelete(int id);
}
