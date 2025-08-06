package com.Dushyant.SpringSecPractise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecPractiseApplicationTests {

	@Test
	void contextLoads() {
		assertEquals(10, 2+8);
	}

}
