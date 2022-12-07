package com.bcafinance.sanspringboot.swagger;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 07/12/2022 9:38
Last Modified on 07/12/2022 9:38
Version 1.0
*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
        @Bean
        public Docket api(){
            return new Docket(DocumentationType.SWAGGER_2).
                    select().
                    apis(RequestHandlerSelectors.basePackage("com.bcafinance.sanspringboot.controllers")).
                    paths(PathSelectors.any()).build()
                    .apiInfo(apiInfo());
        }

    private ApiInfo apiInfo()
    {
        ApiInfo apiInfo = new ApiInfo(
                "SpringBootRestAPI",//title
                "Springboot bootcamp ITDP 7 BCAF",//descriptions
                "v1.0",//version
                "Term Of Services URL is On Progress",//termsOfServiceUrl
                new Contact("Sandhy Akmal Nasution","localhost:8080/api/v1","sandhyakmal21@gmail.com"),//Contact Name
                "Springboot Open Source License",//license
                "https://spring.io/",//licenseUrl
                Collections.emptyList()
        );
        return apiInfo;
    }
}
