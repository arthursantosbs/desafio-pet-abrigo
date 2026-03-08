package br.com.abrigo.main;

import br.com.abrigo.services.ArquivoService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArquivoService leitor = new ArquivoService();
        boolean rodando = true;

        System.out.println("Bem vindo ao sistema de adoção de pets!");
        while (rodando) {
            System.out.println("|n ---Menu Inicial---");
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
                        // Aqui você pode chamar o método para cadastrar um novo pet
                        leitor.lerPerguntas();
                        //adicionar a captação dos dados do pet e o processo de cadastro
                        break;
                    case 2:
                        System.out.println("Iniciando alteração dos dados do pet cadastrado...");
                        // Aqui você pode chamar o método para alterar os dados de um pet cadastrado
                        break;
                    case 3:
                        System.out.println("Iniciando processo de deleção de pet cadastrado...");
                        // Aqui você pode chamar o método para deletar um pet cadastrado
                        break;
                    case 4:
                        System.out.println("Listando todos os pets cadastrados...");
                        // Aqui você pode chamar o método para listar todos os pets cadastrados
                        break;
                    case 5:
                        System.out.println("Listando pets por algum critério...");
                        // Aqui você pode chamar o método para listar pets por algum critério
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