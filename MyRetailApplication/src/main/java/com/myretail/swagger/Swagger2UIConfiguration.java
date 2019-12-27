package com.myretail.swagger;

import java.util.Collections;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * Swagger configuration
 * */




@EnableSwagger2
@Configuration
public class Swagger2UIConfiguration {

	@Bean
	public Docket postsApi(ServletContext servletContext) {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/v1/products/*"))
				.apis(RequestHandlerSelectors.basePackage("com.myretail.controller"))
				.build().pathProvider(new RelativePathProvider(servletContext) {
	                @Override
	                public String getApplicationBasePath() {
	                    return "/v1/products";
	                }
	            })
				.apiInfo(apiDetails());
	}

	
	private ApiInfo apiDetails() {
		
		return new ApiInfo(
				"Spring Boot Rest API",
				"Retail Product API",
				"1.0",
				"Demo project",
				new springfox.documentation.service.Contact("Sree Hari", "http://localhost:9010/v1/products/13860428", "alechali.sri1@e@gmail.com"),
				"API License",
				"www.google.com",
				Collections.emptyList())				
				;
		
	}
}
