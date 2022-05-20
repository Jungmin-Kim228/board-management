package com.nhnacademy.jdbc.board.compre.mapper;

import com.nhnacademy.jdbc.board.compre.dto.PostDTO;
import com.nhnacademy.jdbc.board.compre.domain.Pagination;
import com.nhnacademy.jdbc.board.compre.domain.Post;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface PostMapper {

    Optional<PostDTO> selectPost(@Param("id") int id);

    void postRegister(@Param("Post") Post post, @Param("num") int num);

    void postUpdate(@Param("id") int id, @Param("title") String title, @Param("content") String content);

    void postDelete(@Param("id") int id);

    void postRecover(@Param("id") int id);

    int postCount();

    List<PostDTO> getListPage(final Pagination pagination);
}
