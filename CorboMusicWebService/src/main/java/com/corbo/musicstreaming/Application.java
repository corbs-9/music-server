package com.corbo.musicstreaming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import com.corbo.musicstreaming.config.CassandraService;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = "com.corbo.musicstreaming")
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@Configuration
@EnableAsync
@EnableSwagger2
public class Application {
	
	//Unused because Spring handles it all...
	@SuppressWarnings("unused")
	@Autowired
	private static CassandraService cassandraService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("musicServer").select().build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Corbo Music Server").description("A RESTful Music Web Server")
				.contact("Corbo").license("Apache License Version 2.0")
				.licenseUrl("https://github.com/corbs9/music-server/blob/v0.1/LICENSE").version("2.0").build();
	}
}
