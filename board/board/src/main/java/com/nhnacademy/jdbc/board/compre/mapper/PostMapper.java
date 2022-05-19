package com.nhnacademy.jdbc.board.compre.mapper;

import com.nhnacademy.jdbc.board.compre.dao.PostDAO;
import com.nhnacademy.jdbc.board.compre.domain.Post;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface PostMapper {

    Optional<PostDAO> selectPost(@Param("id") int id);

    List<PostDAO> selectPosts();

    void postRegister(@Param("Post") Post post, @Param("num") int num);

    void postUpdate(int id, Post post);

    void postDelete(int id);
}
