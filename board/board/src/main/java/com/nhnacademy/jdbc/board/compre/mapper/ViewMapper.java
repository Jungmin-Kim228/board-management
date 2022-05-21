package com.nhnacademy.jdbc.board.compre.mapper;

import com.nhnacademy.jdbc.board.compre.domain.View;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface ViewMapper {

    List<View> findViewer(@Param("postNo")int postNo);

    View isView(@Param("postNo")int postNo, @Param("userId")String userId);

    void insertView(@Param("postNo")int postNo, @Param("userNo")int userNo);
}
