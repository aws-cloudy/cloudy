package com.s207.cloudy;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.config.location=" +
		"classpath:/application.yml"
)
class CloudyApplicationTests {

}
