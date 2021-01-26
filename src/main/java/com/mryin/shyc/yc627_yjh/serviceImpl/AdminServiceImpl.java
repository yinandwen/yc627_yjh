package com.mryin.shyc.yc627_yjh.serviceImpl;

import com.mryin.shyc.yc627_yjh.mapper.AdminMapper;
import com.mryin.shyc.yc627_yjh.pojo.Admin;
import com.mryin.shyc.yc627_yjh.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper ;

    @Override
    public Admin queryAdminByUsername(String username , String password) {
        return adminMapper.queryAdminByUsername(username , password);
    }
}
