package com.mryin.shyc.yc627_yjh.mapper;

import com.mryin.shyc.yc627_yjh.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserMapper {
    List<User> queryAllUser();

    int addUser(User user);

    int deleteUser(@Param("username")String username);

    int updateUser(User user);

    User queryAllUserByUsername(@Param("username") String username);
}
