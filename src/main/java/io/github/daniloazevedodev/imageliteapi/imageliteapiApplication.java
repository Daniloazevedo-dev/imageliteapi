package io.github.daniloazevedodev.imageliteapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class imageliteapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(imageliteapiApplication.class, args);
	}

}
