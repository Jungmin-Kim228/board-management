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
    private String writer;

    @Getter
    @Setter
    private String content;

    @Getter
    private final Date writeDate;

    @Getter
    @Setter
    private Date modifyDate;

    @Getter
    @Setter
    private int commentCount;

    @Getter
    @Setter
    private boolean checkHide;

    public Post(int id, String title, String writer, String content, Date writeDate, int commentCount, boolean checkHide) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.writeDate = writeDate;
        this.commentCount = commentCount;
        this.checkHide = checkHide;
    }

    public Post(String title, String content, Date writeDate) {
        this.title = title;
        this.content = content;
        this.writeDate = writeDate;
    }
}
