package io.maia.reactorplayground;

import io.maia.reactorplayground.config.BrandClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(BrandClientConfig.class)
@SpringBootApplication
public class CarXPApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarXPApplication.class, args);
	}

}
