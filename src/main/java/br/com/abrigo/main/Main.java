package br.com.abrigo.main;

import br.com.abrigo.services.ArquivoService;

public class Main {
    public static void main(String[] args) {
        // Criamos o nosso "trabalhador" de arquivos
        ArquivoService leitor = new ArquivoService();

        System.out.println("Bem-vindo ao Sistema de Adoção de Pets!");
        System.out.println("Carregando formulário base...\n");

        // Mandamos ele trabalhar!
        leitor.exibirPerguntas();
    }
}