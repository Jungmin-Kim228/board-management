package com.nhnacademy.jdbc.board.compre.domain;

import lombok.Getter;
import lombok.Setter;

public class Comment {
    @Getter
    private final int commentNo;

    @Getter
    private final String commentWriter;

    @Getter
    @Setter
    private String content;

    public Comment(int commentNo, String commentWriter, String content) {
        this.commentNo = commentNo;
        this.commentWriter = commentWriter;
        this.content = content;
    }
}
