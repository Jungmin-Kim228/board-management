package com.nhnacademy.jdbc.board.compre.service;

import com.nhnacademy.jdbc.board.compre.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Integer getUser(String id);

    List<User> getUsers();

    boolean successLogin(String id, String password);
}
