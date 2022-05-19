package com.nhnacademy.jdbc.board.compre.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class Post {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String content;

    @Getter
    private String writer;

    @Getter
    private Date writeDate;

    @Getter
    @Setter
    private Date modifyDate;

    @Getter
    @Setter
    private int commentCount;

    public Post(int id, String title, String writer, Date writeDate, int commentCount) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.writeDate = writeDate;
        this.modifyDate = null;
        this.commentCount = commentCount;
    }

    public Post(String title, String content, Date writeDate) {
        this.title = title;
        this.content = content;
        this.writeDate = writeDate;
    }
}
