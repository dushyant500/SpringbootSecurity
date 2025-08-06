package com.Dushyant.SpringSecPractise;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI mallOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-MALL Services")
                        .description("This Service is used to manage Mall-shops")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Dushyant Online Firm")
                                .email("dushyantJi@gmail.com")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("mall-api")
                .packagesToScan("com.Dushyant.SpringSecPractise")
                .build();
    }
}
