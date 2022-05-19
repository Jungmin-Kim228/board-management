package com.nhnacademy.jdbc.board.compre.service.impl;

import com.nhnacademy.jdbc.board.compre.domain.User;
import com.nhnacademy.jdbc.board.compre.mapper.UserMapper;
import com.nhnacademy.jdbc.board.compre.service.UserService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {
    private final UserMapper userMapper;

    public DefaultUserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Integer getUser(String id) {
        if(Objects.isNull(userMapper.selectUser(id))) {
            return 0;
        }
        return userMapper.selectUser(id);
    }

    @Override
    public List<User> getUsers() {
        return userMapper.selectUsers();
    }

    @Override
    public boolean successLogin(String id, String password) {
        return userMapper.doLogin(id, password).isPresent();
    }

}
