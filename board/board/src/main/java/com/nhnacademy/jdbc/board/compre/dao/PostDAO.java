package com.nhnacademy.jdbc.board.compre.dao;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostDAO {

    int postNo;

    int userNo;

    String postTitle;

    String postContent;

    Date postWriteDatetime;

    Date postModifyDatetime;

    boolean postCheckHide;

    int postHits;
}
