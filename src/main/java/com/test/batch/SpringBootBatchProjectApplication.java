package com.test.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories({ "com.test.batch.repository"})
@ComponentScan(basePackages="com.test.batch")
public class SpringBootBatchProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBatchProjectApplication.class, args);
	}
}
