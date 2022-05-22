package com.nhnacademy.jdbc.board.compre.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class User {

    @NotNull
    int userNo;

    @NotNull
    @Size(min = 1, max = 20, message = "아이디는 1~20글자 사이여야합니다.")
    String userId;

    @NotNull
    @Size(min = 1, max = 20, message = "비밀번호는 1~20글자 사이여야합니다.")
    String userPw;

    @NotNull
    boolean checkAdmin;
}
