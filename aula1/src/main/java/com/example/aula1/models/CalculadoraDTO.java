package com.example.aula1.models;

public class CalculadoraDTO {
    private Double numero1;
    private Double numero2;
    private String operacao;
    private Double resultado;

    public CalculadoraDTO calcular() {
        switch (operacao) {
            case "soma":
                resultado = numero1 + numero2;
                break;
            case "subtracao":
                resultado = numero1 - numero2;
                break;
            case "multiplicacao":
                resultado = numero1 * numero2;
                break;
            case "divisao":
                resultado = numero1 / numero2;
                break;
            default:
                resultado = 0.0;

        }
        return this;
    }

    public Double getNumero1() {
        return numero1;
    }

    public void setNumero1(Double numero1) {
        this.numero1 = numero1;
    }

    public Double getNumero2() {
        return numero2;
    }

    public void setNumero2(Double numero2) {
        this.numero2 = numero2;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }
}
