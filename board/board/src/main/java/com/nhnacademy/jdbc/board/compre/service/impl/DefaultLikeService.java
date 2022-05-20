package com.nhnacademy.jdbc.board.compre.service.impl;

import com.nhnacademy.jdbc.board.compre.mapper.LikeMapper;
import com.nhnacademy.jdbc.board.compre.service.LikeService;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class DefaultLikeService implements LikeService {

    private final LikeMapper likeMapper;

    public DefaultLikeService(LikeMapper likeMapper) {
        this.likeMapper = likeMapper;
    }

    @Override
    public boolean userLike(int postId, int userId) {
        return (likeMapper.userLike(postId, userId)).isPresent();
    }

    @Override
    public void likeInsert(int postId, int userId) {
        likeMapper.likeInsert(postId, userId);
    }

    @Override
    public void likeDelete(int postId, int userId) {
        likeMapper.likeDelete(postId, userId);
    }
}
