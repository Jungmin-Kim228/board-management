package com.nhnacademy.jdbc.board.compre.mapper;

import com.nhnacademy.jdbc.board.compre.domain.User;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface UserMapper {
    @Select("SELECT user_no FROM Users WHERE user_id = #{id}")
    @ResultType(Integer.class)
    Integer selectUser(@Param("id") String id);

    @Select("SELECT * FROM Users")
    List<User> selectUsers();

    @Select("SELECT * FROM Users WHERE user_id = #{id} AND user_pw = #{password}")
    Optional<User> doLogin(@Param("id") String id, @Param("password") String password);
}
