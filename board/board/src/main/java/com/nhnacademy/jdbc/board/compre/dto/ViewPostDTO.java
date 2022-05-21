package com.nhnacademy.jdbc.board.compre.dto;

import java.util.Date;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

public class ViewPostDTO {
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String title;

    @Getter
    private String writer;

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

    @Getter
    @Setter
    private boolean isLike;

    public ViewPostDTO(int id, String title, String writer, Date writeDate, Date modifyDate, int commentCount, boolean checkHide) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.writeDate = writeDate;
        this.commentCount = commentCount;
        if(Objects.isNull(modifyDate)) {
            this.modifyDate = writeDate;
        } else {
            this.modifyDate = modifyDate;
        }
        this.checkHide = checkHide;
        this.isLike = false;
    }

    public ViewPostDTO(String title, Date writeDate) {
        this.title = title;
        this.writeDate = writeDate;
    }
}
