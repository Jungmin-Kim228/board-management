package com.nhnacademy.jdbc.board.compre.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserDTO {

    int userNum;

    private final String id;

    private final String pw;

    public UserDTO(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }
}
