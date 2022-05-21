package com.nhnacademy.jdbc.board.compre.mapper;

import com.nhnacademy.jdbc.board.compre.domain.Post;
import com.nhnacademy.jdbc.board.compre.domain.Pagination;
import com.nhnacademy.jdbc.board.compre.domain.PostMainView;
import com.nhnacademy.jdbc.board.compre.dto.PostDTO;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface PostMapper {

    Optional<Post> selectPost(@Param("id") int id);

    List<PostMainView> selectPosts();

    void postRegister(@Param("Post") PostDTO postDTO, @Param("num") int num);

    void postUpdate(@Param("id") int id, @Param("title") String title, @Param("content") String content, @Param("date") Date date);

    void postDelete(@Param("id") int id);

    void postRecover(@Param("id") int id);

    int postCount();

    List<PostMainView> getListPage(final Pagination pagination);

    List<PostMainView> searchPost(@Param("title") String title);
}
