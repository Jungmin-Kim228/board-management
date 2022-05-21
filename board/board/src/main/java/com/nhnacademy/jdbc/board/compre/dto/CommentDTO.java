package com.nhnacademy.jdbc.board.compre.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CommentDTO {
    private final int commentNo;

    private final String commentWriter;

    private String content;

    public CommentDTO(int commentNo, String commentWriter, String content) {
        this.commentNo = commentNo;
        this.commentWriter = commentWriter;
        this.content = content;
    }
}
