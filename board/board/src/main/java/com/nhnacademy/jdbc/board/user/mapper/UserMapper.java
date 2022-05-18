package com.nhnacademy.jdbc.board.user.mapper;

import com.nhnacademy.jdbc.board.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface UserMapper {
    Optional<User> selectUser(String id);

    List<User> selectUsers();

    Optional<User> doLogin(@Param("id") String id,@Param("password") String password);
}
