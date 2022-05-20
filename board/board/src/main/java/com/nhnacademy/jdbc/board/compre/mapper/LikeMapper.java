package com.nhnacademy.jdbc.board.compre.mapper;

import com.nhnacademy.jdbc.board.compre.dto.LikeDTO;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface LikeMapper {
    Optional<LikeDTO> userLike(@Param("postNo") int postNo, @Param("userNo") int userNo);

    void likeInsert(@Param("postNo") int postNo, @Param("userNo") int userNo);

    void likeDelete(@Param("postNo") int postNo, @Param("userNo") int userNo);
}
