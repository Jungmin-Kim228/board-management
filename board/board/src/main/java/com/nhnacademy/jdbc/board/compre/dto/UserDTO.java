package com.nhnacademy.jdbc.board.compre.dto;

import lombok.Getter;
import lombok.Setter;

public class UserDTO {

    @Getter
    @Setter
    int userNum;
    @Getter
    private final String id;

    @Getter
    private final String pw;

    public UserDTO(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }
}
