package br.com.abrigo.services;

import br.com.abrigo.entities.Pet;
import br.com.abrigo.exceptions.ValidacaoPetException;
import java.util.List;

import java.util.Scanner;

public class PetService {
    public void iniciarCadastro(List<String> perguntas, Scanner scanner) {
        // Lógica para iniciar o cadastro do pet
        Pet pet = new Pet();
        // pergunta 1: nome completo
        System.out.println(perguntas.get(0));
        String nome = scanner.nextLine().trim();
        if(nome.isEmpty() || !nome.contains(" ")) {
            throw new ValidacaoPetException("O Pet deve ter um nome completo (nome e sobrenome).");
        }
        if (!nome.matches("[a-zA-Z\\s]+$")) {
            throw new ValidacaoPetException("O nome do Pet deve conter apenas letras e espaços.");
        }
        pet.setNomeCompleto(nome);
    }
}
