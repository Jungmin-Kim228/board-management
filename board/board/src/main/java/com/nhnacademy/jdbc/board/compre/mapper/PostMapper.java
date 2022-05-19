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

    List<Post> selectPosts();

    void postRegister(@Param("Post") Post post, @Param("num") int num);

    void postUpdate(int id, Post post);

    void postDelete(int id);
}
