package com.nhnacademy.jdbc.board.compre.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class View {

    @NotNull
    int postNo;

    @NotNull
    @Size(min = 1, max = 20, message = "아이디는 1~20글자 사이여야합니다.")
    String userId;
}
