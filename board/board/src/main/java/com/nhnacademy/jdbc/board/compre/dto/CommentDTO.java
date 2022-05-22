package com.nhnacademy.jdbc.board.compre.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDTO {
    @NotNull
    private final int commentNo;

    @NotNull
    @Size(min = 1, max = 20, message = "아이디는 1~20글자 사이여야합니다.")
    private final String commentWriter;

    @NotNull
    @Size(min = 1, max = 100, message = "내용은 1~100글자 사이여야합니다.")
    private String content;

    public CommentDTO(int commentNo, String commentWriter, String content) {
        this.commentNo = commentNo;
        this.commentWriter = commentWriter;
        this.content = content;
    }
}
