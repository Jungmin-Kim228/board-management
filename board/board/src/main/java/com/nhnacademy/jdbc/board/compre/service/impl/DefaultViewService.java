package com.nhnacademy.jdbc.board.compre.service.impl;

import com.nhnacademy.jdbc.board.compre.domain.View;
import com.nhnacademy.jdbc.board.compre.mapper.ViewMapper;
import com.nhnacademy.jdbc.board.compre.service.ViewService;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class DefaultViewService implements ViewService {
    private final ViewMapper viewMapper;

    public DefaultViewService(ViewMapper viewMapper) {
        this.viewMapper = viewMapper;
    }

    @Override
    public List<View> findView(int postNo) {
        return viewMapper.findViewer(postNo);
    }

    @Override
    public boolean isView(int postNo, String userId) {
        return !Objects.isNull(viewMapper.isView(postNo, userId));
    }

    @Override
    public void insertView(int postNo, int userNo) {
        viewMapper.insertView(postNo, userNo);
    }
}
