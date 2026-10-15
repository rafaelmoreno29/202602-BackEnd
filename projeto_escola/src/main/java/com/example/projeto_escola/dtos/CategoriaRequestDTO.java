package com.example.projeto_escola.dtos;

import lombok.Builder;

@Builder
public class CategoriaRequestDTO {
    private Long id;
    private String nome;

    public CategoriaRequestDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaRequestDTO() {
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
}
