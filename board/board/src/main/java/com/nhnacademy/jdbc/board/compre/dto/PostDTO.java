package com.nhnacademy.jdbc.board.compre.dto;

import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostDTO {

    @NotNull
    private int id;

    @NotNull
    @Size(min = 1, max = 20, message = "제목은 1~20글자 사이여야합니다.")
    private String title;

    @NotNull
    @Size(min = 1, max = 20, message = "아이디는 1~20글자 사이여야합니다.")
    private String writer;

    @NotNull
    @Size(min = 1, max = 100, message = "내용은 1~20글자 사이여야합니다.")
    private String content;

    @NotNull
    private Date writeDate;

    private Date modifyDate;

    @NotNull
    private int commentCount;

    @NotNull
    private boolean checkHide;

    @NotNull
    private boolean isLike;

    @NotNull
    private int viewCount;

    @NotNull
    private byte[] file;

    @NotNull
    @Size(min = 1, max = 50, message = "파일이름은 1~50글자 사이여야합니다.")
    private String filename;

    @NotNull
    private int parent;

    @NotNull
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
