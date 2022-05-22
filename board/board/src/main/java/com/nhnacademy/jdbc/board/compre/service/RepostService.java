package com.nhnacademy.jdbc.board.compre.service;

import com.nhnacademy.jdbc.board.compre.domain.Repost;
import java.util.List;

public interface RepostService {

    Repost getRepost(int postNo);

    List<Repost> getReposts(int postNo);

    void repostInsert(int postNo, int depth);
}
