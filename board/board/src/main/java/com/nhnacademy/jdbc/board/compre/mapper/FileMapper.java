package com.nhnacademy.jdbc.board.compre.mapper;

import com.nhnacademy.jdbc.board.compre.domain.FileData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface FileMapper {
    void fileDownload(@Param("postNo")int postNo);

    FileData uploadFile(@Param("postNo")int postNo);
}
