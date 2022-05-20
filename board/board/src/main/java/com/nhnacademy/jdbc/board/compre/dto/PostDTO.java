package com.nhnacademy.jdbc.board.compre.dto;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostDTO {

    int postNo;

    int userNo;

    String postTitle;

    String postContent;

    Date postWriteDatetime;

    Date postModifyDatetime;

    boolean postCheckHide;

    int postHits;
}
