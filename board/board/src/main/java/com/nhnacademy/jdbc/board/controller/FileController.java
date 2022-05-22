package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.compre.domain.FileData;
import com.nhnacademy.jdbc.board.compre.exception.FileDownloadFailedException;
import com.nhnacademy.jdbc.board.compre.service.FileService;
import com.nhnacademy.jdbc.board.compre.service.impl.DefaultFileService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FileController {
    private final FileService fileService;

    public FileController(DefaultFileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/filedownload/{postNo}")
    public String fileDownload(@PathVariable("postNo") int id) {
        FileData fileData = fileService.fileUpload(id);
        byte[] file = fileData.getFileByte();
        String filename = fileData.getFileName();
        String downloadPath = System.getProperty("user.home") + File.separator + "Downloads" + File.separator;
        try (FileOutputStream fos = new FileOutputStream(downloadPath + filename)) {
            fos.write(file);
        } catch (IOException e) {
            throw new FileDownloadFailedException("File Download Failed.");
        }
        return "redirect:/content?id=" + id;
    }
}
