package br.com.ottimizza.regraslistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class RegrasListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegrasListenerApplication.class, args);
	}

}
