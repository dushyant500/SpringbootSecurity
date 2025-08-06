package com.Dushyant.SpringSecPractise;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringSecPractiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecPractiseApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner inspectBeans(ApplicationContext ctx) {
        return args -> {
            System.out.println("=== 🔍 Beans loaded by Spring ===");
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
            	if (beanName.contains("controller") || beanName.contains("Controller"))
                    System.out.println("✅ " + beanName);
            }
        };
    }

}
