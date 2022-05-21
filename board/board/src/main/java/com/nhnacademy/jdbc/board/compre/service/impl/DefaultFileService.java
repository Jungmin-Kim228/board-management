package com.nhnacademy.jdbc.board.compre.service.impl;

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
    public void downloadFile(int postNo) {
        fileMapper.fileDownload(postNo);
    }

    @Override
    public void fileUpload(int postNo) {
        fileMapper.uploadFile(postNo);
    }
}
