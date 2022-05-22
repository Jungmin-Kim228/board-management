package com.nhnacademy.jdbc.board.compre.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Repost {
    int postId;

    int parentId;

    int depth;
}
