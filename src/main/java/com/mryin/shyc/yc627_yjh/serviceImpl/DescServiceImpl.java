package com.mryin.shyc.yc627_yjh.serviceImpl;

import com.mryin.shyc.yc627_yjh.mapper.DescMapper;
import com.mryin.shyc.yc627_yjh.pojo.Desc;
import com.mryin.shyc.yc627_yjh.service.DescService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DescServiceImpl implements DescService {

    @Resource
    private DescMapper descMapper;


    @Override
    public List<Desc> getAllDesc() {
        return descMapper.getAllDesc();
    }

    @Override
    public int deleteDesc(Integer id) {
        return descMapper.deleteDesc(id);
    }

    @Override
    public int addDesc(Desc desc) {
        return descMapper.addDesc(desc);
    }

    @Override
    public int updateDesc(Desc desc) {
        return descMapper.updateDesc(desc);
    }

    @Override
    public Desc getAllDescById(Integer id) {
        return descMapper.getAllDescById(id);
    }
}
