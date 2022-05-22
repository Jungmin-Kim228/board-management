package com.nhnacademy.jdbc.board.compre.dto;

import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class ViewPostDTO {
    @NotNull
    private int id;

    @Size(min = 1, max = 20, message = "아이디는 1~20글자 사이여야합니다.")
    private String title;

    @Size(min = 1, max = 20, message = "아이디는 1~20글자 사이여야합니다.")
    private String writer;

    @NotNull
    private final Date writeDate;

    private Date modifyDate;

    @NotNull
    private int viewCount;

    @NotNull
    private int commentCount;

    @NotNull
    private boolean checkHide;

    @NotNull
    private boolean isLike;

    @NotNull
    private int parent;

    @NotNull
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
