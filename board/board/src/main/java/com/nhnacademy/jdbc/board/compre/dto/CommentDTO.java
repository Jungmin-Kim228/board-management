package com.nhnacademy.jdbc.board.compre.dto;

import lombok.Getter;
import lombok.Setter;

public class CommentDTO {
    @Getter
    private final int commentNo;

    @Getter
    private final String commentWriter;

    @Getter
    @Setter
    private String content;

    public CommentDTO(int commentNo, String commentWriter, String content) {
        this.commentNo = commentNo;
        this.commentWriter = commentWriter;
        this.content = content;
    }
}
