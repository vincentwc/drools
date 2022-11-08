package com.vincent.template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.xjt.field.mapper.**"})
public class FieldAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(FieldAdminApplication.class, args);
    }

}
