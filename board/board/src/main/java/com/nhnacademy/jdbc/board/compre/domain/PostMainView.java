package com.nhnacademy.jdbc.board.compre.domain;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostMainView {
    @NotNull
    int postNo;

    @NotNull
    @Size(min = 1, max = 20, message = "아이디는 1~20글자 사이여야합니다.")
    String userId;

    @NotNull
    @Size(min = 1, max = 20, message = "제목은 1~20글자 사이여야합니다.")
    String postTitle;

    @NotNull
    Date postWriteDatetime;

    Date postModifyDatetime;

    @NotNull
    boolean postCheckHide;

    @NotNull
    int parent;

    @NotNull
    int depth;
}
