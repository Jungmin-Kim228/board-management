package com.nhnacademy.jdbc.board.compre.exception;

public class FileDownloadFailedException extends RuntimeException {
    public FileDownloadFailedException(String msg) {
        super(msg);
    }
}
