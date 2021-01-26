package com.mryin.shyc.yc627_yjh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mryin.shyc.yc627_yjh.mapper")
public class Yc627YjhApplication {

    public static void main(String[] args) {
        SpringApplication.run(Yc627YjhApplication.class, args);
    }

}
