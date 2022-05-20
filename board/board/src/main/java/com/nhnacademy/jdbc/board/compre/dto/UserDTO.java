package com.nhnacademy.jdbc.board.compre.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDTO {

    int userNo;

    String userId;

    String userPw;

    boolean checkAdmin;
}
