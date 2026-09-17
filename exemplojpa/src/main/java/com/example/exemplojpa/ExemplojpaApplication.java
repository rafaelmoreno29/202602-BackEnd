package com.example.exemplojpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.exemplojpa.models.Departamento;
import com.example.exemplojpa.models.Disciplina;
import com.example.exemplojpa.models.Professor;
import com.example.exemplojpa.repositories.DepartamentoRepository;
import com.example.exemplojpa.repositories.DisciplinaRepository;
import com.example.exemplojpa.repositories.ProfessorRepository;

@SpringBootApplication
public class ExemplojpaApplication {

	@Bean
	public CommandLineRunner init(
			@Autowired ProfessorRepository professorRepository,
			@Autowired DepartamentoRepository departamentoRepository,
			@Autowired DisciplinaRepository disciplinaRepository) {
		return args -> {
			// Salvar um departamento
			Departamento dpto1 = departamentoRepository.save(
					new Departamento(null, "Departamento de Informática"));
			// Salvar disciplinas
			disciplinaRepository.save(new Disciplina(null, "BD"));
			disciplinaRepository.save(new Disciplina(null, "POO"));
			disciplinaRepository.save(new Disciplina(null, "Redes"));

			// Buscar todas as disciplinas
			List<Disciplina> disciplinas = disciplinaRepository.getAll();

			// Salvar um professor
			professorRepository.save(
					new Professor(null, "Rafael Moreno", "Doutor",
							100.0, dpto1, disciplinas));
			// Salvar um professor
			professorRepository.save(
					new Professor(null, "José da Silva", "Mestre",
							90.0, dpto1, disciplinas));

			// Buscar todos os professores
			List<Professor> professores = professorRepository.findAll();
			professores.forEach(System.out::println);

			System.out.println("Professores com valor hora aula"
					+ "	maior que 95.0");
			professores = professorRepository.findByValorHoraAulaGreaterThan(95.0);
			professores.forEach(System.out::println);

			System.out.println("Professor com nome Rafael");
			professores = professorRepository.findByNomeLike("Rafael%");
			professores.forEach(System.out::println);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ExemplojpaApplication.class, args);
	}

}
