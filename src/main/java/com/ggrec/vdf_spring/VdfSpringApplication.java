package com.ggrec.vdf_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication
public class VdfSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(VdfSpringApplication.class, args);
	}
}
