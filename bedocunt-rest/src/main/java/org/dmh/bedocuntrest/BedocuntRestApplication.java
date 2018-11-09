package org.dmh.bedocuntrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class BedocuntRestApplication  extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(BedocuntRestApplication.class, args);
	}
	
	 @Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(BedocuntRestApplication .class);
	    }
	 
	 
	 //https://dzone.com/articles/building-a-web-app-using-spring-boot-angular-6-and
}
