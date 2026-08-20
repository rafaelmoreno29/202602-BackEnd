package com.example.exemplojpa.models;

import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 1000)
    private String nome;
    private String titulacao;
    // @Column(check = @CheckConstraint(
    // constraint = "valor_hora_aula >= 10 AND valor_hora_aula <= 500"))
    private Double valorHoraAula;

    public Professor(Long id, String nome, String titulacao, Double valorHoraAula) {
        this.id = id;
        this.nome = nome;
        this.titulacao = titulacao;
        this.valorHoraAula = valorHoraAula;
    }

    public Professor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public Double getValorHoraAula() {
        return valorHoraAula;
    }

    public void setValorHoraAula(Double valorHoraAula) {
        this.valorHoraAula = valorHoraAula;
    }

    @Override
    public String toString() {
        return "Professor [id=" + id + ", nome=" + nome + ", titulacao=" + titulacao + ", valorHoraAula="
                + valorHoraAula + "]";
    }
}
