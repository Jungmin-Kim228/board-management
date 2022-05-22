package com.nhnacademy.jdbc.board.compre.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Comment {

    @NotNull
    int commentNo;

    @NotNull
    int postNo;

    @NotNull
    int userNo;

    @NotNull
    @Size(min = 1, max = 50, message = "1 ~ 50 사이 글자를 입력하셔야합니다.")
    String commentContent;
}
