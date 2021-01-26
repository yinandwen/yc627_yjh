package com.mryin.shyc.yc627_yjh.mapper;


import com.mryin.shyc.yc627_yjh.pojo.Desc;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
@Mapper
public interface DescMapper {
    public List<Desc> getAllDesc();

    int deleteDesc(Integer id);

    int addDesc(Desc desc);

    int updateDesc(Desc desc);

    public Desc getAllDescById(Integer id);
}
