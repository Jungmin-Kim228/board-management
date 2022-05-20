package com.nhnacademy.jdbc.board.compre.service;

import com.nhnacademy.jdbc.board.compre.domain.Pagination;
import com.nhnacademy.jdbc.board.compre.dto.PostDTO;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<PostDTO> getPost(int id);

    List<PostDTO> getPosts();

    void register(PostDTO postDTO, int num);

    void update(int id, String title, String content, Date date);

    void delete(int id);

    void recover(int id);
    
    int getCount();

    List<PostDTO> getListPage(final Pagination pagination);

    List<PostDTO> searchPost(String title);
}
