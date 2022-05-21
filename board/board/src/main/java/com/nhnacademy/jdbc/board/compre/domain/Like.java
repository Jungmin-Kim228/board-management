package com.nhnacademy.jdbc.board.compre.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Like {

    int likeNo;

    int postNo;

    String userId;
}
