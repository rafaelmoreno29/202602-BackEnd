package com.example.exemplojpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.exemplojpa.models.Professor;

public interface ProfessorRepository
        extends JpaRepository<Professor, Long> {

    public List<Professor> findByNomeLike(String nome);

    public List<Professor> findByValorHoraAulaGreaterThan(Double valor);

    // public List<Professor>
    // findByTitulacaoAndValorHoraAulaGreaterThan(String titulacao,
    // Double valor);

    @Query("SELECT p FROM Professor p WHERE p.titulacao = ?1 " +
            "AND p.valorHoraAula > ?2")
    public List<Professor> findByTituloEValorHora(String titulacao,
            Double valor);
}