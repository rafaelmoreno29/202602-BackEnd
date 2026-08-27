package com.example.exemplojpa.models;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

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
    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;
    @ManyToMany
    @JoinTable(name = "Professor_Disciplina", joinColumns = {
            @JoinColumn(name = "Professor_ID") }, inverseJoinColumns = { @JoinColumn(name = "Disciplina_ID") })
    private List<Disciplina> disciplinas;

    public Professor(Long id, String nome, String titulacao,
            Double valorHoraAula, Departamento departamento,
            List<Disciplina> disciplinas) {
        this.id = id;
        this.nome = nome;
        this.titulacao = titulacao;
        this.valorHoraAula = valorHoraAula;
        this.departamento = departamento;
        this.disciplinas = disciplinas;
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

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Professor [id=" + id + ", nome=" + nome +
                ", titulacao=" + titulacao + ", valorHoraAula="
                + valorHoraAula + ", departamento=" + departamento + "]";
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
