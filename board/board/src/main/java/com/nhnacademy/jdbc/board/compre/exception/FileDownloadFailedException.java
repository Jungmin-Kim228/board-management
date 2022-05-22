package com.nhnacademy.jdbc.board.compre.exception;

public class FileDownloadFailedException extends RuntimeException {
    private Exception Exception;

    public FileDownloadFailedException(Exception exception) {
        Exception = exception;
    }
}
