package com.nhnacademy.jdbc.board.compre.dto;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostDTO {

    private int id;

    private String title;

    private String writer;

    private String content;

    private final Date writeDate;

    private Date modifyDate;

    private int commentCount;

    private boolean checkHide;

    private boolean isLike;

    private int viewCount;

    private int depth;

    private byte[] file;

    private String filename;

    public PostDTO(int id, String title, String writer,
                   String content, Date writeDate, Date modifyDate,
                   int commentCount, boolean checkHide) {
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
    }

    public PostDTO(int id, String title, String writer,
                   String content, Date writeDate, Date modifyDate,
                   int commentCount, boolean checkHide, String filename, byte[] file) {
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
        this.filename = filename;
        this.file = file;
    }

    public PostDTO(String title, String content, Date writeDate, byte[] file, String filename) {
        this.title = title;
        this.content = content;
        this.writeDate = writeDate;
        this.file = file;
        this.filename = filename;
    }

    public PostDTO(String title, String content, Date writeDate) {
        this.title = title;
        this.content = content;
        this.writeDate = writeDate;
    }
}
