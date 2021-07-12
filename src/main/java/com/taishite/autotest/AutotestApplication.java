package com.taishite.autotest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = {"com.taishite.autotest"})
@SpringBootApplication
@Slf4j
public class AutotestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutotestApplication.class, args);
		log.info("系统启动成功");
	}

}


