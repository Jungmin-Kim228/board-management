package com.nhnacademy.jdbc.board.compre.domain;

import lombok.Getter;
import lombok.Setter;

public class User {

    @Getter
    @Setter
    int userNum;
    @Getter
    private final String id;

    @Getter
    private final String pw;

    public User(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }
}
