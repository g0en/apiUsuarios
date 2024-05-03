package com.apiUsuarios;

import com.apiUsuarios.Controllers.UserController;
import com.apiUsuarios.Models.Dto.UserEntityDTO;
import com.apiUsuarios.Models.ERole;
import com.apiUsuarios.Models.RoleEntity;
import com.apiUsuarios.Models.UserEntity;
import com.apiUsuarios.Repositories.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Users APIS",version = "1.0",description = "Users Management Apis."))
public class ApiUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiUsuariosApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Bean
	CommandLineRunner init() {
		return args -> {
			UserEntity user = UserEntity.builder()
					.username("goen")
					.email("franco@gmail.com")
					.password(passwordEncoder.encode("12franco34"))
					.roles(Set.of(RoleEntity.builder()
							.name(ERole.valueOf(ERole.ADMIN.name()))
							.build()))
					.build();
			this.userRepository.save(user);

			UserEntity user2 = UserEntity.builder()
					.username("termitente")
					.email("ezequiel@gmail.com")
					.password(passwordEncoder.encode("12ezequiel34"))
					.roles(Set.of(RoleEntity.builder()
							.name(ERole.valueOf(ERole.USER.name()))
							.build()))
					.build();
			this.userRepository.save(user2);

			UserEntity user3 = UserEntity.builder()
					.username("quiet")
					.email("barroso@gmail.com")
					.password(passwordEncoder.encode("12barroso34"))
					.roles(Set.of(RoleEntity.builder()
							.name(ERole.valueOf(ERole.INVITED.name()))
							.build()))
					.build();
			this.userRepository.save(user3);
		};
	}

}
