package com.comma.fit.fitcrm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan({"com.comma.fit.fitcrm.market.mapper", "com.comma.fit.fitcrm.common.mapper"})
@EnableTransactionManagement
public class FitCrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitCrmApplication.class,args);
    }
}

