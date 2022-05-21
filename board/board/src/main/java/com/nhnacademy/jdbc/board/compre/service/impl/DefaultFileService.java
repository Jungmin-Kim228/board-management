package com.nhnacademy.jdbc.board.compre.service.impl;

import com.nhnacademy.jdbc.board.compre.domain.FileData;
import com.nhnacademy.jdbc.board.compre.mapper.FileMapper;
import com.nhnacademy.jdbc.board.compre.service.FileService;
import org.springframework.stereotype.Service;

@Service
public class DefaultFileService implements FileService {
    private final FileMapper fileMapper;

    public DefaultFileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }
    @Override
    public FileData fileUpload(int postNo) {
        return fileMapper.uploadFile(postNo);
    }
}
