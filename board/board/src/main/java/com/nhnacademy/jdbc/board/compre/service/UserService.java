package com.nhnacademy.jdbc.board.compre.service;

import com.nhnacademy.jdbc.board.compre.domain.User;
import java.util.List;

public interface UserService {

    Integer getUser(String id);

    List<User> getUsers();

    String getUserId(int num);

    boolean successLogin(String id, String password);
}
