package com.nhnacademy.jdbc.board.compre.domain;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Post {

    int postNo;

    int userNo;

    String postTitle;

    String postContent;

    Date postWriteDatetime;

    Date postModifyDatetime;

    boolean postCheckHide;

    int postHits;

    String filename;

    byte[] file;
}
