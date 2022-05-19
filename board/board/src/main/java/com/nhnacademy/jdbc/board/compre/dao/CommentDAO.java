package com.nhnacademy.jdbc.board.compre.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CommentDAO {

    int commentNo;

    int postNo;

    int userNo;

    String commentContent;
}
