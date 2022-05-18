package com.nhnacademy.jdbc.board.user.domain;

import lombok.Getter;

public class User {

    @Getter
    private final String id;

    @Getter
    private final String pw;

    public User(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }
}
