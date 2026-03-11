package br.com.abrigo.services;

import br.com.abrigo.entities.Pet;

import java.text.Normalizer;
import java.util.List;
import java.util.Scanner;

public class BuscaService {
    private String termoPesquisado = "";

    public List<Pet> iniciarBusca(List<Pet> pets, Scanner scanner) {
        System.out.println("Qual o tipo do animal? (1) Cachorro (2) Gato");
        String tipoStr = scanner.nextLine().toUpperCase().trim();
        if (tipoStr.equals("1")){
            pets.removeIf(p -> !p.getTipoPet().name().equals("CACHORRO"));
        }else if (tipoStr.equals("2")){
            pets.removeIf(p -> !p.getTipoPet().name().equals("GATO"));
        }else {
            System.out.println("opção invalida, cancelando a busca");
        }

        System.out.println("Escolha o 1° critério de busca");
        System.out.println("1 - Nome | 2 - Sexo | 3 - idade | 4 - peso | 5- raça | 6 - Endereço");
        System.out.println("Opção: ");
        String criterio1 = scanner.nextLine().trim();
        aplicarFiltro(pets, criterio1, scanner);

        imprimirResultado(pets);
        return pets;
        }
    private void aplicarFiltro(List<Pet> pets,String opcao, Scanner scanner) {
        switch (opcao) {
            case "1":
                System.out.print("Digite o nome do animal, ou parte dele: ");
                termoPesquisado = limparTexto(scanner.nextLine().trim());
                pets.removeIf(p -> !limparTexto(p.getNomeCompleto()).contains(termoPesquisado));
                break;
            case "2":
                System.out.print("Digite o sexo do animal (Macho ou Fêmea): ");
                String sexoStr = limparTexto(scanner.nextLine());
                pets.removeIf(p -> !limparTexto(p.getSexoPet().name()).equals(sexoStr));
                break;
            case "3":
                System.out.print("Digite o idade do animal(Em anos): ");
                try{
                    double idade = Double.parseDouble(scanner.nextLine().trim());
                    pets.removeIf(p -> p.getIdade() != idade);
                } catch (NumberFormatException e) {
                    System.out.println("Idade inválida. Filtro ignorado.");
                }
                break;
            case "4":
                System.out.print("Digite o peso (em kg):");
                try {
                    double peso = Double.parseDouble(scanner.nextLine().trim());
                    pets.removeIf(p -> p.getPeso() != peso);
                }catch (NumberFormatException e) {
                    System.out.println("peso inválido. Filtro ignorado.");
                }
                break;
            case "5":
                System.out.print("Digite a raça");
                termoPesquisado = limparTexto(scanner.nextLine().trim());
                pets.removeIf(p -> !limparTexto(p.getRaca()).contains(termoPesquisado));
                break;
            default:
                System.out.println("Opção de critério inválida.");
        }
    }
    private String limparTexto(String texto) {
        if (texto == null) return "";
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        return textoNormalizado.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").toLowerCase().trim();
    }
    private void imprimirResultado(List<Pet> pets) {
        System.out.println("Resultado da busca:");
        if (pets.isEmpty()) {
            System.out.println("Nenhum pet encontrado");
        }
        int contador = 1;
        for (Pet pet : pets) {
            String nome = pet.getNomeCompleto();

            if (!termoPesquisado.isEmpty()) {
                nome = nome.replaceAll("(?i)" + termoPesquisado, "\u001B[31m$0\u001B[0m");
            }
            System.out.println(contador + " - " + nome + " - " +
                    pet.getTipoPet() + " - " + pet.getSexoPet() + " - "
                    + pet.getEndereco() + " - " + pet.getIdade() + "anos - " +
                    pet.getPeso() + "kg - " + pet.getRaca());
            contador++;
        }
    }

}
