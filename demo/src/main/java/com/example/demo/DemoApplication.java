package com.example.demo;

import com.example.demo.entities.CourseEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.demo.entities.Level.*;
import static com.example.demo.entities.UserRole.*;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication {
	private final UserRepository userRepository;
	private final CourseRepository courseRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	void setup(){

		userRepository.save(UserEntity.builder()
				.firstname("Oriana")
				.lastname("Trif")
				.email("oriana@email.com")
				.password(passwordEncoder.encode("1234"))
				.role(ADMINISTRATOR)
				.build()
		);

		userRepository.save(UserEntity.builder()
				.firstname("Iulia")
				.lastname("Zdrenghea")
				.email("iulia@email.com")
				.password(passwordEncoder.encode("0000"))
				.role(USER)
				.build()
		);
		userRepository.save(UserEntity.builder()
				.firstname("Andrei")
				.lastname("Marian")
				.email("andrei@yahool.com")
				.password(passwordEncoder.encode("7437"))
				.role(USER)
				.build()
		);

		courseRepository.save(CourseEntity.builder()
				.title("Design")
				.description("Design Course")
				.rating(10)
				.assignments("yes")
				.level(BEGINNER)
				.build()
		);

		courseRepository.save(CourseEntity.builder()
				.title("Sweets")
				.description("Deserts and sweets")
				.rating(8)
				.assignments("np")
				.level(INTERMEDIATE)
				.build()
		);

		courseRepository.save(CourseEntity.builder()
				.title("Programming")
				.description("Java course")
				.rating(9)
				.assignments("yes")
				.level(ADVANCED)
				.build()
		);

	}


}
