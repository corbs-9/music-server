package com.corbo.musicstreaming;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan(basePackages = "com.corbo.musicstreaming")
@EnableAutoConfiguration
@Configuration
public class Application {

	public static void main(String[] args) {
		String userHome = System.getProperty("user.home");
		try {
			Path path = Paths.get(new URI("file:///"+userHome+"/.musicservice"));
			if (!Files.exists(path)) {
				Files.createDirectories(path);
			}
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		SpringApplication.run(Application.class, args);
	}

}
