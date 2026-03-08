package br.com.abrigo.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ArquivoService {
    private static final String CAMINHO_FORMULARIO = "formulario.txt";

    public List<String> lerPerguntas() {
        Path caminho = Paths.get(CAMINHO_FORMULARIO);
        try {
            return Files.readAllLines(caminho);
        } catch (IOException e) {
            // Se der erro lá em cima, ele cai aqui
            System.err.println("Erro Crítico: Não foi possível ler o arquivo de formulário. " + e.getMessage());
            return null;
        }
    }
}
