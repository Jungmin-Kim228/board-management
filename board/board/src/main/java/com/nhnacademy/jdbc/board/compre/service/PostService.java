package com.nhnacademy.jdbc.board.compre.service;

import com.nhnacademy.jdbc.board.compre.dao.PostDAO;
import com.nhnacademy.jdbc.board.compre.domain.Pagination;
import com.nhnacademy.jdbc.board.compre.domain.Post;
import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<Post> getPost(int id);

    void register(Post post, int num);

    void update(int id, String title, String content);

    void delete(int id);

    void recover(int id);
    
    int getCount();

    List<Post> getListPage(final Pagination pagination);
}
