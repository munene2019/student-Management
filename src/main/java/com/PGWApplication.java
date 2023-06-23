package com;

import com.proxy.MessageProducer;
import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableKafka
public class PGWApplication {

	public static void main(String[] args) {
		System.out.println("Application Running");
		SpringApplication.run(PGWApplication.class, args);
		System.out.println("Payment Gateway started successfully....");

	}

	@Bean
	public OkHttpClient okHttpClient() {
		return new OkHttpClient();
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public MessageProducer messageProducer() {
		return new MessageProducer();


	}
}
