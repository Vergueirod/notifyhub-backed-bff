package com.vergueiro_group.notifyhub_backend_bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NotifyhubBackendBffApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotifyhubBackendBffApplication.class, args);
	}

}
