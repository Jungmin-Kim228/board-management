package com.nhnacademy.jdbc.board.compre.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class Post {

    @Getter
    private int id;

    @Getter
    @Setter
    private String title;

    @Getter
    private final String writer;

    @Getter
    private final Date writeDate;

    @Getter
    @Setter
    private Date modifyDate;

    @Getter
    @Setter
    private int commentCount;

    public Post(String title, String writer, Date writeDate) {
        this.title = title;
        this.writer = writer;
        this.writeDate = writeDate;
        this.modifyDate = null;
        this.commentCount = 0;
    }
}
