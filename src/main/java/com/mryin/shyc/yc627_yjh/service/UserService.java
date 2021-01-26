package com.mryin.shyc.yc627_yjh.service;


import com.mryin.shyc.yc627_yjh.pojo.User;

import java.util.List;

public interface UserService {
    List<User> queryAllUser();
    int addUser(User user);
    int deleteUser(String username);
    int updateUser(User user);
    User queryAllUserByUsername(String username);
}
