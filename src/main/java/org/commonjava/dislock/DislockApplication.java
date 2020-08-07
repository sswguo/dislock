package org.commonjava.dislock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.commonjava"})
public class DislockApplication {

	public static void main(String[] args) {
		SpringApplication.run(DislockApplication.class, args);
	}

}
