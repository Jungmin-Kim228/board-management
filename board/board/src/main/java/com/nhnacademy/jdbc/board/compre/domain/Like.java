package com.nhnacademy.jdbc.board.compre.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Like {

    @NotNull
    int likeNo;

    @NotNull
    int postNo;

    @NotNull
    @Size(min = 1, max = 20, message = "1 ~ 20 사이 아이디여야합니다.")
    String userId;
}
