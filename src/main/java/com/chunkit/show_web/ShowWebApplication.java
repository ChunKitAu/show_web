package com.chunkit.show_web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan({"com.chunkit.show_web.mapper","com.chunkit.show_web.auth.mapper"})
@SpringBootApplication
public class ShowWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShowWebApplication.class, args);
    }


}
