package com.nhnacademy.jdbc.board.compre.mapper;

import com.nhnacademy.jdbc.board.compre.domain.Repost;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface RepostMapper {
    Repost selectRepost(@Param("postNo") int postNo);

    List<Repost> selectReposts(@Param("postNo") int postNo);

    void insertRepost(@Param("postNo") int postNo,@Param("depth") int depth);
}
