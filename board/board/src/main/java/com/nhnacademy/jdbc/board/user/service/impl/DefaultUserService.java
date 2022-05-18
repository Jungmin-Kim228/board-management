package com.nhnacademy.jdbc.board.user.service.impl;

import com.nhnacademy.jdbc.board.user.domain.User;
import com.nhnacademy.jdbc.board.user.mapper.UserMapper;
import com.nhnacademy.jdbc.board.user.service.UserService;
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
    public Optional<User> getUser(String id) {
        if(Objects.isNull(userMapper.selectUser(id))) {
            return Optional.empty();
        }
        return userMapper.selectUser(id);
    }

    @Override
    public List<User> getUsers() {
        return userMapper.selectUsers();
    }

    @Override
    public boolean successLogin(String id, String password) {
        return !Objects.isNull(userMapper.doLogin(id, password));
    }
}
