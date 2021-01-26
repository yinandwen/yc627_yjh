package com.mryin.shyc.yc627_yjh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Desc {
    private Integer id ; //id
    private Integer projectId ; // 项目id
    private String problemType ; //问题类型
    private String problemDesc ; //问题描述
    private String projectManager ; //负责人
    private Date startDate ; //项目开始日期
    private String image ;  //图片描述
}
