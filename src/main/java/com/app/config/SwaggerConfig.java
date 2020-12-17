package com.app.config;

import java.util.ArrayList;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfig {
	@Bean
	public Docket createDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.app.controller"))
				.paths(PathSelectors.regex("/api/v1/student.*"))
				.build()
				.apiInfo(apiInfo());
		
	}

	public ApiInfo apiInfo() {
		// TODO Auto-generated method stub
		return new ApiInfo("MY FIRST STUDENT REST FULL APPLICATION",
				"Welocme to My App","1.3.6","https://nareshit.in/",new Contact("Ranjeet","https://nareshit.in/","ranjeetsuman123@gmal.com"), "NareshIt Lincence", "https://nareshit.in/", new ArrayList<>());
	}

	

}
