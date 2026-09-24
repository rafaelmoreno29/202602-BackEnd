package com.example.exemplojpa.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ExemploController {
    private String nomeApp;
    private Double versaoApp;
    private Double valorPi;

    public ExemploController(String nomeApp,
            @Qualifier("versaoApp") Double versaoApp,
            @Qualifier("valorPi") Double valorPi) {
        this.nomeApp = nomeApp;
        this.versaoApp = versaoApp;
        this.valorPi = valorPi;
    }

    @GetMapping("valorPi")
    public Double getValorPi() {
        return this.valorPi;
    }

    @GetMapping("versao")
    public Double getVersao() {
        return this.versaoApp;
    }

    @GetMapping("nomeApp")
    public String getNomeApp() {
        return this.nomeApp;
    }

}
