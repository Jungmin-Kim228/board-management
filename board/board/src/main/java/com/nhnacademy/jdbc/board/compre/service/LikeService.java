package com.nhnacademy.jdbc.board.compre.service;

public interface LikeService {
    boolean userLike(int postId, int userId);

    void likeInsert(int postId, int userId);

    void likeDelete(int postId, int userId);
}
