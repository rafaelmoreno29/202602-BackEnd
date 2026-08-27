package com.example.exemplojpa.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.exemplojpa.models.Disciplina;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class DisciplinaRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Disciplina save(Disciplina Disciplina) {
        return entityManager.merge(Disciplina);
    }

    public List<Disciplina> getAll() {
        return entityManager
                .createQuery("from Disciplina", Disciplina.class).getResultList();
    }

    public Disciplina getById(Long id) {
        return entityManager.find(Disciplina.class, id);
    }

    @Transactional
    public void delete(Disciplina Disciplina) {
        entityManager.remove(Disciplina);
    }
}
