package com.example.IdentityReconciliation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class IdentityReconciliationApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdentityReconciliationApplication.class, args);
	}

}
