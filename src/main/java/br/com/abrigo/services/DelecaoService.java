package br.com.abrigo.services;

import br.com.abrigo.entities.Pet;

import java.util.List;
import java.util.Scanner;

public class DelecaoService {

    public boolean iniciarDelecao(List<Pet> petsEncontrados, Scanner scanner, ArquivoService arquivoService) {
        if (petsEncontrados.isEmpty()) {
            return true;
        }
        System.out.println("Digite o número do pet que deseja deletar (0 para cancelar): ");
        int escolha = -1;
        try{
            escolha = Integer.parseInt(scanner.nextLine().trim());
        }catch(NumberFormatException e){
            System.out.println("Entrada inválida. Por favor, digite um número válido." + e.getMessage());
            return false;
        }
        if (escolha == 0) {
            System.out.println("Deleção cancelada.");
            return true;
        }
        if (escolha < 1 || escolha > petsEncontrados.size()) {
            System.out.println("Número inválido, reiniciando busca...");
            return false;
        }
        Pet petEscolhido = petsEncontrados.get(escolha -1);

        System.out.println("ATENÇÃO VOCÊ TEM CERTEZA QUE DESEJA DELETAR O PET?" +
                petEscolhido.getNomeCompleto() + " (SIM/NAO)");
        String confirmacao = scanner.nextLine().trim().toUpperCase();
        if (confirmacao.equals("SIM")) {
            boolean deletado = arquivoService.deletarPet(petEscolhido);
            if (deletado) {
                System.out.println("Deletando o pet " + petEscolhido.getNomeCompleto() + "...");
            }else {
                System.out.println("Erro: O arquivo do pet não foi encontrado.");
            }
        }else{
            System.out.println("deleção cancelada. O pet foi está  salvo!");
        }
        return true;


    }
}
