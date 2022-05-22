package com.nhnacademy.jdbc.board.compre.domain;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Post {

    @NotNull
    int postNo;

    @NotNull
    int userNo;

    @NotNull
    @Size(min = 1, max = 20, message = "제목은 1~20글자 사이여야합니다.")
    String postTitle;

    @NotNull
    @Size(min = 1, max = 100, message = "내용은 1~100글자 사이여야합니다.")
    String postContent;

    @NotNull
    Date postWriteDatetime;

    Date postModifyDatetime;

    @NotNull
    boolean postCheckHide;

    @NotNull
    int parent;

    @NotNull
    int depth;

    @NotNull
    @Size(min = 1, max = 50, message = "파일이름은 1~50글자 사이여야합니다.")
    String filename;

    @NotNull
    byte[] file;
}
