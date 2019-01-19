package com.ggrec.vdf_spring;

import com.ggrec.vdf_spring.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(AppProperties.class)
@SpringBootApplication
public class VdfSpringApplication {

	public static void main(String[] args) {
	    SpringApplication.run(VdfSpringApplication.class, args);
	}

}
