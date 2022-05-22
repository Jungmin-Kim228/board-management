package com.nhnacademy.jdbc.board.compre.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    @NotNull
    int userNum;

    @NotNull
    @Size(min = 1, max = 20, message = "아이디는 1~20글자 사이여야합니다.")
    private final String id;

    @NotNull
    @Size(min = 1, max = 20, message = "비밀번호는 1~20글자 사이여야합니다.")
    private final String pw;

    public UserDTO(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }
}
