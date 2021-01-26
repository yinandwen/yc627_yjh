package com.mryin.shyc.yc627_yjh.mapper;

import com.mryin.shyc.yc627_yjh.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AdminMapper {

    Admin queryAdminByUsername(@Param("username") String username , @Param("password") String password);
}
