package com.mryin.shyc.yc627_yjh.service;


import com.mryin.shyc.yc627_yjh.pojo.Admin;

public interface AdminService {

    Admin queryAdminByUsername(String username , String password);

}
