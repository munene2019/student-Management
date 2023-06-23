package com;

import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication

@Configuration
public class PGWApplication {

	public static void main(String[] args) {
		System.out.println("Application Running");
		SpringApplication.run(PGWApplication.class, args);
		System.out.println("Here we are");

	}
	@Bean
	public OkHttpClient okHttpClient() {
		return new OkHttpClient();
	}




}
