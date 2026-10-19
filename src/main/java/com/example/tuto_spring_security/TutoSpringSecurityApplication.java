package com.example.tuto_spring_security;

import com.example.tuto_spring_security.role.Role;
import com.example.tuto_spring_security.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class TutoSpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutoSpringSecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(RoleRepository roleRepository){
		return args -> {
			Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
			if (userRole.isEmpty()){
				final Role role = new Role();
				role.setName("ROLE_USER");
				role.setCreatedBy("APP");
				roleRepository.save(role);
			}
		};
	}

}
