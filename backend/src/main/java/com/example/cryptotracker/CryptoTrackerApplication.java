package com.example.cryptotracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

@SpringBootApplication
public class CryptoTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoTrackerApplication.class, args);
	}

	@Bean
	public SesClient sesClient() {
		return SesClient.builder()
				.region(Region.US_EAST_1)
				.build();
	}

}
