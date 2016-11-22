package ru.jirs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@ComponentScan
public class TransportApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TransportApplication.class, args);
	}
}
