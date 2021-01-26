package com.mryin.shyc.yc627_yjh.service;


import com.mryin.shyc.yc627_yjh.pojo.Desc;

import java.util.List;

public interface DescService {

    List<Desc> getAllDesc();

    int deleteDesc(Integer id);

    int addDesc(Desc desc);

    int updateDesc(Desc desc);

    Desc getAllDescById(Integer id);
}
