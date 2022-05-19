package com.nhnacademy.jdbc.board.compre.service;

public interface UserService {

    Integer getUser(String id);

    String getUserId(int num);

    boolean successLogin(String id, String password);
}
