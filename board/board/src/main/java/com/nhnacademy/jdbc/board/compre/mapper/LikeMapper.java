package com.nhnacademy.jdbc.board.compre.mapper;

import com.nhnacademy.jdbc.board.compre.domain.Like;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface LikeMapper {
    Optional<Like> userLike(@Param("postNo") int postNo, @Param("userId") String userId);

    void likeInsert(@Param("postNo") int postNo, @Param("userNo") int userNo);

    void likeDelete(@Param("postNo") int postNo, @Param("userNo") int userNo);
}
