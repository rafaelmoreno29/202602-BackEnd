package com.example.projeto_escola.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.projeto_escola.domains.Categoria;
import com.example.projeto_escola.dtos.CategoriaRequestDTO;
import com.example.projeto_escola.repositories.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    private CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public CategoriaRequestDTO inserir(CategoriaRequestDTO categRequestDTO) {
        Categoria categoria = categoriaRepository.save(
                new Categoria(null, categRequestDTO.getNome(), null));

        return new CategoriaRequestDTO(categoria.getId(), categoria.getNome());
    }

    @Override
    public CategoriaRequestDTO editar(Long id, CategoriaRequestDTO cursoRequestDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editar'");
    }

    @Override
    public List<CategoriaRequestDTO> obterTodos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obterTodos'");
    }

    @Override
    public CategoriaRequestDTO obterPorId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obterPorId'");
    }

    @Override
    public void excluir(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'excluir'");
    }

}
