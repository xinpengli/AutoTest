package com.taishite.autotest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
@SpringBootTest(classes =AutotestApplication.class )
class AutotestApplicationTests extends AbstractTestNGSpringContextTests{

	@Test
	void contextLoads() {
    int a =3;
    int b =3;
		String driverPath =this.getClass().getClassLoader().getResource("").getPath();
		log.info((driverPath));


	}

}
