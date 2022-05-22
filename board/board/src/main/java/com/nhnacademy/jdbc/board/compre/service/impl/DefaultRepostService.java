package com.nhnacademy.jdbc.board.compre.service.impl;

import com.nhnacademy.jdbc.board.compre.domain.Repost;
import com.nhnacademy.jdbc.board.compre.service.RepostService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DefaultRepostService implements RepostService {
    @Override
    public Repost getRepost(int postNo) {
        return null;
    }

    @Override
    public List<Repost> getReposts(int postNo) {
        return null;
    }

    @Override
    public void repostInsert(int postNo, int depth) {

    }
}
