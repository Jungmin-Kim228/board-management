package com.nhnacademy.jdbc.board.compre.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CommentDTO {

    int commentNo;

    int postNo;

    int userNo;

    String commentContent;
}
