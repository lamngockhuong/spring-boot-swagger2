package com.ngockhuong.swagger2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo metaData() {
//        return new ApiInfo(
//                "My REST API",
//                "Some custom description of API.",
//                "1.0.0",
//                "Terms of service",
//                new Contact("Lam Ngoc Khuong", "www.ngockhuong.com", "me@ngockhuong.com"),
//                "License of API",
//                "API license URL",
//                Collections.emptyList()
//        );
//    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ngockhuong.swagger2.controller"))
                .paths(PathSelectors.regex("/product.*"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("My REST API")
                .description("ome custom description of API.")
                .version("1.0.0")
                .license("Ngoc Khuong")
                .licenseUrl("https://www.ngockhuong.com")
                .contact(new Contact("Lam Ngoc Khuong", "www.ngockhuong.com", "me@ngockhuong.com"))
                .build();
    }
}
