package com.sptwin.sptserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableCaching
@EnableSwagger2
@MapperScan("com.sptwin.sptserver.base.mapper")
//@ImportResource({"classpath:mybatis-config.xml","classpath:mybatis-config.xml"})
public class SptserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SptserverApplication.class, args);
	}
}
