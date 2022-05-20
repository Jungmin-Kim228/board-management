package com.nhnacademy.jdbc.board.compre.mapper;

import com.nhnacademy.jdbc.board.compre.domain.User;
import com.nhnacademy.jdbc.board.compre.dto.UserDTO;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface UserMapper {
    Integer selectUserNum(@Param("id") String id);

    String selectUserId(@Param("num") int num);

    Optional<UserDTO> doLogin(@Param("id") String id, @Param("password") String password);

    Optional<User> findUser(@Param("id") int id);
}
