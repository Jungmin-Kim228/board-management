package com.nhnacademy.jdbc.board.compre.domain;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostMainView {
    int postNo;

    String userId;

    String postTitle;

    Date postWriteDatetime;

    Date postModifyDatetime;

    boolean postCheckHide;

    int postHits;
}
