package com.chunkit.show_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(value = "com.chunkit.show_web.mapper")
@SpringBootApplication
public class ShowWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShowWebApplication.class, args);
    }


}
