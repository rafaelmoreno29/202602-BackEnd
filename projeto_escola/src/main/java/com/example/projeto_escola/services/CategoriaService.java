package com.example.projeto_escola.services;

import java.util.List;

import com.example.projeto_escola.dtos.CategoriaRequestDTO;

public interface CategoriaService {
    CategoriaRequestDTO inserir(CategoriaRequestDTO cursoRequestDTO);

    CategoriaRequestDTO editar(Long id, CategoriaRequestDTO cursoRequestDTO);

    List<CategoriaRequestDTO> obterTodos();

    CategoriaRequestDTO obterPorId(Long id);

    void excluir(Long id);
}
