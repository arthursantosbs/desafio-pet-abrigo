package br.com.abrigo.services;

import br.com.abrigo.entities.Pet;


import java.util.List;
import java.util.Scanner;

public class AlteracaoService {
    public void iniciarAlteracao(List<Pet> petsEconntrados, Scanner scanner) {
        System.out.println("Digite o nome do pet:");
        if (petsEconntrados.isEmpty()) {
            return;
        }
        int escolha = -1;
        while (true) {
            System.out.print("Digite o NÚMERO do pet que deseja alterar (0 para cancelar): ");
            try {
                escolha = Integer.parseInt(scanner.nextLine().trim());
                if (escolha == 0) {
                    System.out.println("Alteração cancelada.");
                    return;
                }
                if (escolha > 0 && escolha <= petsEconntrados.size()){
                    break;
                }else{
                    System.out.println("Opção inválida. Por favor, escolha um número entre 1 e " + petsEconntrados.size() + ", ou 0 para cancelar.");
                }
            }catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número válido." + e.getMessage());
            }
            Pet petEscolhido = petsEconntrados.get(escolha-1);
            Pet petAtualizado = new Pet();

            petAtualizado.setTipoPet(petEscolhido.getTipoPet());
            petAtualizado.setSexoPet(petEscolhido.getSexoPet());
            System.out.println("Alterando os dados de" + petEscolhido.getNomeCompleto());
            System.out.print("Digite o novo nome completo (ou deixe em branco para manter o atual): ");
            String novoNome = scanner.nextLine().trim();
            petAtualizado.setNomeCompleto(novoNome.isEmpty() ? petEscolhido.getNomeCompleto() : novoNome);

            System.out.print("Nova Idade [" + petEscolhido.getIdade() + "]: ");
            String novaIdade = scanner.nextLine().trim();
            if(novaIdade.isEmpty()){
                petAtualizado.setIdade(petEscolhido.getIdade());
            }else{
                petAtualizado.setIdade(Double.parseDouble(novaIdade));
            }

            System.out.print("Novo peso [" + petEscolhido.getPeso() + "]: ");
            String novoPeso = scanner.nextLine().trim();
            if(novoPeso.isEmpty()){
                petAtualizado.setPeso(petEscolhido.getPeso());
            }else{
                petAtualizado.setPeso(Double.parseDouble(novoPeso));
            }

            System.out.println("Nova raça ["+ petEscolhido.getRaca() + "]: ");
            String novaRaca = scanner.nextLine().trim();
            petAtualizado.setRaca(novaRaca.isEmpty() ? petEscolhido.getRaca() : novaRaca);

            ArquivoService arquivoService = new ArquivoService();
            arquivoService.atualizarPet(petEscolhido, petAtualizado);

            System.out.println("Dados atualizados com sucesso!");
        }

    }
}
