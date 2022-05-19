package com.nhnacademy.jdbc.board.compre.mapper;

import com.nhnacademy.jdbc.board.compre.domain.User;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface UserMapper {
    Integer selectUser(@Param("id") String id);

    List<User> selectUsers();

    Optional<User> doLogin(@Param("id") String id, @Param("password") String password);
}
