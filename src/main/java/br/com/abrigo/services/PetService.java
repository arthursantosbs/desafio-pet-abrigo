package br.com.abrigo.services;

import br.com.abrigo.entities.Pet;
import br.com.abrigo.enums.SexoPet;
import br.com.abrigo.enums.TipoPet;
import br.com.abrigo.exceptions.ValidacaoPetException;
import java.util.List;

import java.util.Scanner;

public class PetService {
    private static final String NAO_INFORMADO = "Não informado";
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
        //perguntas sobre o tipo do pet
        System.out.println(perguntas.get(1));
        String tipoStr = scanner.nextLine().toUpperCase().trim();
        if (tipoStr.equals("CACHORRO")){
            pet.setTipoPet(TipoPet.CACHORRO);
        }else if (tipoStr.equals("GATO")){
            pet.setTipoPet(TipoPet.GATO);
        }else {
            throw new ValidacaoPetException("Tipo de pet inválido. Por favor, informe 'Cachorro' ou 'Gato'.");
        }
            //perguntas sobre o sexo do pet
        System.out.println(perguntas.get(2));
        String sexoStr = scanner.nextLine().toUpperCase().trim();
        if (sexoStr.equals("MACHO")){
            pet.setSexoPet(SexoPet.MACHO);
        }else if (sexoStr.equals("FEMEA")) {
            pet.setSexoPet(SexoPet.FEMEA);
        }else{
            throw new ValidacaoPetException("Sexo de pet inválido. Por favor, informe 'Macho' ou 'Fêmea'.");
        }

        //pergunta sobre o endereço
        System.out.println(perguntas.get(3));
        System.out.print("Rua: ");
        String rua = scanner.nextLine().trim();
        System.out.println("Número da casa(se não tiver, deixe vaxio): ");
        String numero = scanner.nextLine().trim();
        if (numero.isEmpty() || !numero.matches("[0-9]+")) {
            numero = NAO_INFORMADO;
        }
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine().trim();
        String enderecoCompleto = rua + ", " + numero + ", " + cidade;
        pet.setEndereco(enderecoCompleto);
        System.out.println("Endereço registrado: " + pet.getEndereco());


        //lendo a idade do pet
        System.out.println(perguntas.get(4));
        System.out.print("Idade (em anos): ");
        String idadeStr = scanner.nextLine().trim().replace(",", ".");
        if(idadeStr.isEmpty()) {
            pet.setIdade(0);
            System.out.println("Idade registrada: " + NAO_INFORMADO);
        } else {
            double idadeCalculada = Double.parseDouble(idadeStr);
            if (idadeCalculada < 0) {
                throw new ValidacaoPetException("A idade do pet não pode ser negativa.");
            }
            System.out.print("Esse valor é em anos? (S/N): ");
            String tipoIdade = scanner.nextLine().trim().toUpperCase();
            if (tipoIdade.equals("N")) {
                idadeCalculada = idadeCalculada / 12; // Convertendo para meses
            }else if (!tipoIdade.equals("S")) {
                throw new ValidacaoPetException("Resposta inválida. Por favor, responda com 'S' para anos ou 'N' para meses.");
            }

            if (idadeCalculada > 20) {
                throw new ValidacaoPetException("A idade do pet deve ser inferior a 20 anos.");
            }
            pet.setIdade(idadeCalculada);
            System.out.println("Idade registrada: " + pet.getIdade() + " anos");

        }

        //lendo o peso do pet
        System.out.println(perguntas.get(5));
        System.out.print("Peso (em kg): ");
        String pesoStr = scanner.nextLine().trim().replace(",", ".");
        if (pesoStr.isEmpty()) {
            pet.setPeso(0.0);
            System.out.println("Peso registrado: " + NAO_INFORMADO);
        }else {
            double peso = Double.parseDouble(pesoStr);
            if (peso < 0.5 || peso > 60) {
                throw new ValidacaoPetException("O peso do pet deve estar entre 0.5 kg e 60 kg.");
            }
            pet.setPeso(peso);
            System.out.println("Peso registrado: " + pet.getPeso() + " kg");
        }


        //lendo a raça do pet
        System.out.println(perguntas.get(6));
        String raca = scanner.nextLine().trim();
        if (raca.isEmpty()) {
            raca = NAO_INFORMADO;
        }else if (!raca.matches("[a-zA-ZÀ-ÿ\\s]+$")){
            throw new ValidacaoPetException("A raça do Pet deve conter apenas letras e espaços.");
        }
        pet.setRaca(raca);
        System.out.println("Raça registrada: " + pet.getRaca());

        System.out.print("Pet salvo!");
        ArquivoService arquivoService = new ArquivoService();
        arquivoService.salvarPetEmArquivo(pet);
    }
}
