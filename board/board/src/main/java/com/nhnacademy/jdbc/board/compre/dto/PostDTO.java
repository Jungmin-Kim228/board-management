package com.nhnacademy.jdbc.board.compre.dto;

import java.util.Date;
import java.util.Objects;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostDTO {

    private int id;

    private String title;

    private String writer;

    private String content;

    private Date writeDate;

    private Date modifyDate;

    private int commentCount;

    private boolean checkHide;

    private boolean isLike;

    private int viewCount;
    private byte[] file;

    private String filename;

    private int parent;

    private int depth;

    public PostDTO(int id, String title, String writer,
                   String content, Date writeDate, Date modifyDate,
                   int commentCount, boolean checkHide, int parent, int depth) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
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

    public PostDTO(int id, String title, String writer,
                   String content, Date writeDate, Date modifyDate,
                   boolean checkHide, String filename, byte[] file, int parent, int depth) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.writeDate = writeDate;
        if(Objects.isNull(modifyDate)) {
            this.modifyDate = writeDate;
        } else {
            this.modifyDate = modifyDate;
        }
        this.checkHide = checkHide;
        this.isLike = false;
        this.filename = filename;
        this.file = file;
        this.parent = parent;
        this.depth = depth;
    }

    public PostDTO(String title, String content, Date writeDate, byte[] file, String filename, int parent, int depth) {
        this.title = title;
        this.content = content;
        this.writeDate = writeDate;
        this.file = file;
        this.filename = filename;
        this.parent = parent;
        this.depth = depth;
    }

    public PostDTO(String title, String content, Date writeDate, int parent, int depth) {
        this.title = title;
        this.content = content;
        this.writeDate = writeDate;
        this.parent = parent;
        this.depth = depth;
    }
}
