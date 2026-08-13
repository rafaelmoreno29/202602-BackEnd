package com.example.aula1.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.aula1.models.CalculadoraDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class MeuController {
    @GetMapping()
    public String getOlaMundo() {
        return "Olá Mundo!";
    }

    @GetMapping("/com-nome")
    public String getOlaComNome(@RequestParam() String nome,
            @RequestParam("s") String sobrenome) {
        return "Olá " + nome + " " + sobrenome + "!";
    }

    @GetMapping("/cliente/{id}")
    public String getClienteId(@PathVariable() Integer id) {
        return "Cliente " + id;
    }

    @PostMapping("/calcular")
    public CalculadoraDTO postCalcular(@RequestBody CalculadoraDTO entity) {
        return entity.calcular();
    }

}
