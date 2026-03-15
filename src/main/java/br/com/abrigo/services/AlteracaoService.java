package br.com.abrigo.services;

import br.com.abrigo.entities.Pet;


import java.util.List;
import java.util.Scanner;

public class AlteracaoService {
    public void iniciarAlteracao(List<Pet> petsEncontrados, Scanner scanner) {
        if (petsEncontrados.isEmpty()) {
            return;
        }
        int escolha = -1;
        while (true) {
            System.out.print("Digite o NÚMERO do pet que deseja alterar (0 para cancelar): ");
            try {
                String input = scanner.nextLine().trim();
                escolha = Integer.parseInt(input);
                if (escolha == 0) {
                    System.out.println("Alteração cancelada.");
                    return;
                }
                if (escolha > 0 && escolha <= petsEncontrados.size()){
                    Pet petEscolhido = petsEncontrados.get(escolha - 1);
                    realizarAlteracao(petEscolhido, scanner);
                    break;
                } else {
                    System.out.println("Opção inválida. Por favor, escolha um número entre 1 e " + petsEncontrados.size() + ", ou 0 para cancelar.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número válido.");
            }
        }
    }

    private void realizarAlteracao(Pet petEscolhido, Scanner scanner) {
        Pet petAtualizado = new Pet();
        petAtualizado.setTipoPet(petEscolhido.getTipoPet());
        petAtualizado.setSexoPet(petEscolhido.getSexoPet());
        petAtualizado.setEndereco(petEscolhido.getEndereco());
        petAtualizado.setArquivoOrigem(petEscolhido.getArquivoOrigem());

        System.out.println("Alterando os dados de: " + petEscolhido.getNomeCompleto());
        System.out.print("Digite o novo nome completo (ou deixe em branco para manter o atual): ");
        String novoNome = scanner.nextLine().trim();
        petAtualizado.setNomeCompleto(novoNome.isEmpty() ? petEscolhido.getNomeCompleto() : novoNome);

        System.out.print("Nova Idade [" + petEscolhido.getIdade() + "]: ");
        String novaIdadeStr = scanner.nextLine().trim().replace(",", ".");
        if (novaIdadeStr.isEmpty()) {
            petAtualizado.setIdade(petEscolhido.getIdade());
        } else {
            try {
                petAtualizado.setIdade(Double.parseDouble(novaIdadeStr));
            } catch (NumberFormatException e) {
                System.out.println("Idade inválida, mantendo a anterior.");
                petAtualizado.setIdade(petEscolhido.getIdade());
            }
        }

        System.out.print("Novo peso [" + petEscolhido.getPeso() + "]: ");
        String novoPesoStr = scanner.nextLine().trim().replace(",", ".");
        if (novoPesoStr.isEmpty()) {
            petAtualizado.setPeso(petEscolhido.getPeso());
        } else {
            try {
                petAtualizado.setPeso(Double.parseDouble(novoPesoStr));
            } catch (NumberFormatException e) {
                System.out.println("Peso inválido, mantendo o anterior.");
                petAtualizado.setPeso(petEscolhido.getPeso());
            }
        }

        System.out.print("Nova raça [" + petEscolhido.getRaca() + "]: ");
        String novaRaca = scanner.nextLine().trim();
        petAtualizado.setRaca(novaRaca.isEmpty() ? petEscolhido.getRaca() : novaRaca);

        ArquivoService arquivoService = new ArquivoService();
        arquivoService.atualizarPet(petEscolhido, petAtualizado);
        System.out.println("Dados atualizados com sucesso!");
    }
}
