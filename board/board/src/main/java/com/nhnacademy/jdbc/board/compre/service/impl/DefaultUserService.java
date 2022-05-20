package com.nhnacademy.jdbc.board.compre.service.impl;

import com.nhnacademy.jdbc.board.compre.mapper.UserMapper;
import com.nhnacademy.jdbc.board.compre.service.UserService;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {
    private final UserMapper userMapper;

    public DefaultUserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Integer getUser(String id) {
        if(Objects.isNull(userMapper.selectUserNum(id))) {
            return 0;
        }
        return userMapper.selectUserNum(id);
    }

    @Override
    public String getUserId(int num) {
        if(Objects.isNull(userMapper.selectUserId(num))) {
            return null;
        }
        return userMapper.selectUserId(num);
    }
    @Override
    public boolean successLogin(String id, String password) {
        return userMapper.doLogin(id, password).isPresent();
    }

    @Override
    public boolean checkAdmin(int id) {
        if(Objects.isNull(userMapper.findUser(id))) {
            return false;
        }
        return userMapper.findUser(id).get().isCheckAdmin();
    }
}
