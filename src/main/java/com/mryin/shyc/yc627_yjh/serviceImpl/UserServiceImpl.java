package com.mryin.shyc.yc627_yjh.serviceImpl;

import com.mryin.shyc.yc627_yjh.mapper.UserMapper;
import com.mryin.shyc.yc627_yjh.pojo.User;
import com.mryin.shyc.yc627_yjh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int deleteUser(String username) {
        return userMapper.deleteUser(username);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public User queryAllUserByUsername(String username) {
        return userMapper.queryAllUserByUsername(username);
    }

}
