package com.alura.demo;

import com.alura.demo.principal.Principal;
import com.alura.demo.service.ConsumoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApiApplication implements CommandLineRunner {

	@Autowired
	private Principal principal;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApiApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		principal.exibeMenu();
	}
}
