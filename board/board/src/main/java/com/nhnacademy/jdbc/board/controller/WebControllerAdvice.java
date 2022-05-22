package com.nhnacademy.jdbc.board.controller;

import com.nhnacademy.jdbc.board.compre.exception.FileDownloadFailedException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebControllerAdvice {
    @ExceptionHandler(FileDownloadFailedException.class)
    public String handleFileDownloadFailedException(FileDownloadFailedException ex, ModelMap modelMap) {
        modelMap.put("exception", ex);
        return "File Download Failed.";
    }
}
