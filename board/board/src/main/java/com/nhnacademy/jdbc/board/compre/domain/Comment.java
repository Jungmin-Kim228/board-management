package com.nhnacademy.jdbc.board.compre.domain;

import lombok.Getter;
import lombok.Setter;

public class Comment {

    @Getter
    private String commentWriter;

    @Getter
    @Setter
    private String content;

    public Comment(String commentWriter, String content) {
        this.commentWriter = commentWriter;
        this.content = content;
    }
}
