package com.example.exemplojpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.exemplojpa.models.Professor;
import com.example.exemplojpa.repositories.ProfessorRepository;

@SpringBootApplication
public class ExemplojpaApplication {

	@Bean
	public CommandLineRunner init(
			@Autowired ProfessorRepository professorRepository) {
		return args -> {
			// Salvar um professor
			professorRepository.save(
					new Professor(null, "Rafael Moreno", "Doutor", 100.0));

			// Buscar todos os professores
			List<Professor> professores = professorRepository.getAll();
			professores.forEach(System.out::println);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ExemplojpaApplication.class, args);
	}

}
