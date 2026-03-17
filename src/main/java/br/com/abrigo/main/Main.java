package br.com.abrigo.main;

import br.com.abrigo.entities.Pet;
import br.com.abrigo.services.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArquivoService leitor = new ArquivoService();
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n ---Menu Inicial---");
            System.out.println("1. Cadastrar novo pet");
            System.out.println("2 - Alterar os dados do pet cadastrado");
            System.out.println("3 - Deletar um pet cadastrado");
            System.out.println("4 - Listar todos os pets cadastrados");
            System.out.println("5 - Listar pets por algum critério");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");

            String input = sc.nextLine();

            try {
                int opcao = Integer.parseInt(input);

                if (opcao <= 0 || opcao > 6){
                    System.out.println("Opção inválida. Por favor, escolha um número entre 1 e 6.");
                    continue;
                }

                switch (opcao) {
                    case 1:
                        System.out.println("Iniciando cadastro de novo pet...");
                        // Aqui você pode chamar o metodo para cadastrar um novo pet
                        List<String> perguntas = leitor.lerPerguntas();
                        if (perguntas != null) {
                            PetService petService = new PetService();
                            try {
                                petService.iniciarCadastro(perguntas, sc);
                            } catch (Exception e) {
                                System.out.println("Erro durante o cadastro do pet: " + e.getMessage());
                            }
                        }
                        //adicionar a captação dos dados do pet e o processo de cadastro
                        break;
                    case 2:
                        System.out.println("Iniciando alteração dos dados do pet cadastrado...");
                        ArquivoService arquivoService = new ArquivoService();
                        List<Pet> todosOsPets = arquivoService.carregarTodosOsPets();
                        if (todosOsPets.isEmpty()) {
                            System.out.println("não há pets cadastrados para buscar!");
                            break;
                        }
                        BuscaService buscaService = new BuscaService();
                        List<Pet> petsEncontrados = buscaService.iniciarBusca(todosOsPets, sc);

                        AlteracaoService alteracao = new AlteracaoService();
                        alteracao.iniciarAlteracao(petsEncontrados, sc);
                        break;

                    case 3:
                        System.out.println("Iniciando processo de deleção de pet cadastrado...");
                        ArquivoService arqServDelete = new ArquivoService();
                        BuscaService buscaServDelete = new BuscaService();
                        DelecaoService delecaoService = new DelecaoService();

                        List<Pet> todosOsPetsDel = arqServDelete.carregarTodosOsPets();
                        if (todosOsPetsDel.isEmpty()) {
                            System.out.println("Não há pets cadastrados para deletar!");
                            break;
                        }

                        System.out.println("Busque o pet que deseja deletar:");
                        List<Pet> petsEncontradosDel = buscaServDelete.iniciarBusca(todosOsPetsDel, sc);
                        if (!petsEncontradosDel.isEmpty()) {
                            delecaoService.iniciarDelecao(petsEncontradosDel, sc, arqServDelete);
                        }
                        break;
                    case 4:
                        System.out.println("Listando todos os pets cadastrados...");
                        ArquivoService arqServList = new ArquivoService();
                        List<Pet> todosOsPetsList = arqServList.carregarTodosOsPets();
                        if (todosOsPetsList.isEmpty()) {
                            System.out.println("não há pets cadastrados!");
                            break;
                        }
                        // Aqui você pode chamar o metodo para listar todos os pets cadastrados
                        break;
                    case 5:
                        System.out.println("Listando pets por algum critério...");
                        ArquivoService arqServListCrit = new ArquivoService();
                        List<Pet> todosOsPetsListCrit = arqServListCrit.carregarTodosOsPets();
                        if (todosOsPetsListCrit.isEmpty()) {
                            System.out.println("não há pets cadastrados!");
                            break;
                        }
                        BuscaService buscaServListCrit = new BuscaService();

                        buscaServListCrit.iniciarBusca(todosOsPetsListCrit, sc);
                        
                        // Aqui você pode chamar o metodo para listar pets por algum criterio
                        break;
                    case 6:
                        System.out.println("Saindo do sistema. Até mais!");
                        rodando = false;
                        break;
                    default:
                        System.out.println("Opção em construção. Por favor, escolha outra opção.");
                        break;
                }
            }catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite apenas numeros");
            }
        }
        sc.close();
    }
}