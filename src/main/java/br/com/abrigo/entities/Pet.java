package br.com.abrigo.entities;

import br.com.abrigo.enums.SexoPet;
import br.com.abrigo.enums.TipoPet;

public class Pet {
    private String nomeCompleto;
    private TipoPet tipoPet;
    private SexoPet sexoPet;
    private String endereco;
    private double idade;
    private double peso;
    private String raca;


    public Pet() {
    }

    public Pet(String nomeCompleto, TipoPet tipoPet, SexoPet sexoPet, String endereco, double idade, double peso, String raca) {
        this.nomeCompleto = nomeCompleto;
        this.tipoPet = tipoPet;
        this.sexoPet = sexoPet;
        this.endereco = endereco;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public TipoPet getTipoPet() {
        return tipoPet;
    }

    public void setTipoPet(TipoPet tipoPet) {
        this.tipoPet = tipoPet;
    }

    public SexoPet getSexoPet() {
        return sexoPet;
    }

    public void setSexoPet(SexoPet sexoPet) {
        this.sexoPet = sexoPet;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getIdade() {
        return idade;
    }

    public void setIdade(double idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }
}



