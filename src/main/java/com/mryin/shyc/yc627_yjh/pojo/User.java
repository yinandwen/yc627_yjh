package com.mryin.shyc.yc627_yjh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private String realname;
    private String mobile;
    private Integer age ;
    private Integer dept_id;
}
