package com.example.exemplojpa.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.exemplojpa.models.Departamento;
import com.example.exemplojpa.models.Professor;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class DepartamentoRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Departamento save(Departamento departamento) {
        return entityManager.merge(departamento);
    }

    public List<Departamento> getAll() {
        return entityManager
                .createQuery("from Departamento", Departamento.class).getResultList();
    }
}
