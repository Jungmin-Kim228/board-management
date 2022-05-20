package com.nhnacademy.jdbc.board.compre.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class User {

    int userNo;

    String userId;

    String userPw;

    boolean checkAdmin;
}
