package com.nhnacademy.jdbc.board.compre.service;

import com.nhnacademy.jdbc.board.compre.domain.View;
import java.util.List;

public interface ViewService {
    List<View> findView(int postNo);

    boolean isView(int postNo, String userId);

    void insertView(int postNo, int userNo);
}
