package com.github.luuispnp.protcar;

import org.springframework.boot.SpringApplication;

public class TestProtcarApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProtcarApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
