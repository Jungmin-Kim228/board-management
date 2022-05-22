package com.nhnacademy.jdbc.board.compre.dto;

import java.util.Date;
import java.util.Objects;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ViewPostDTO {
    private int id;

    private String title;

    private String writer;

    private final Date writeDate;

    private Date modifyDate;

    private int viewCount;

    private int commentCount;

    private boolean checkHide;

    private boolean isLike;

    private int parent;

    private int depth;

    public ViewPostDTO(int id, String title, String writer, Date writeDate, Date modifyDate, int commentCount, boolean checkHide, int parent, int depth) {
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
        this.parent = parent;
        this.depth = depth;
    }

    public ViewPostDTO(String title, Date writeDate) {
        this.title = title;
        this.writeDate = writeDate;
    }
}
