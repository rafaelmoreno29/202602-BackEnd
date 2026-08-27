package com.example.exemplojpa.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.exemplojpa.models.Professor;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class ProfessorRepository {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Professor save(Professor professor) {
        return entityManager.merge(professor);
    }

    public List<Professor> getAll() {
        return entityManager
                .createQuery("from Professor", Professor.class).getResultList();
    }

    public Professor getById(Long id) {
        return entityManager.find(Professor.class, id);
    }

    @Transactional
    public void delete(Professor professor) {
        entityManager.remove(professor);
    }
}
