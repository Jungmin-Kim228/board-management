package com.nhnacademy.jdbc.board.compre.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Comment {

    int commentNo;

    int postNo;

    int userNo;

    String commentContent;
}
