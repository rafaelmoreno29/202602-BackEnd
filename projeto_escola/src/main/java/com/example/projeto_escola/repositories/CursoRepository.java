package com.example.projeto_escola.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projeto_escola.models.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
