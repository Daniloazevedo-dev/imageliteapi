package io.github.daniloazevedodev.imageliteapi;

import io.github.daniloazevedodev.imageliteapi.domain.entity.Image;
import io.github.daniloazevedodev.imageliteapi.domain.enums.ImageExtension;
import io.github.daniloazevedodev.imageliteapi.infra.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class imageliteapiApplication {

	@Bean
	public CommandLineRunner commandLineRunner(@Autowired ImageRepository repository) {
		return args -> {
			Image image = Image.builder()
					.name("myimage")
					.size(1000L)
					.extension(ImageExtension.PNG)
					.tags("teste")
					.build();

			repository.save(image);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(imageliteapiApplication.class, args);
	}

}
