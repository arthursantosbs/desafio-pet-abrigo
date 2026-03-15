package br.com.abrigo.services;

import br.com.abrigo.entities.Pet;
import br.com.abrigo.enums.SexoPet;
import br.com.abrigo.enums.TipoPet;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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


    public void salvarPetEmArquivo(Pet pet){
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String dataHora = agora.format(formatter);
        String nomeFormatado = pet.getNomeCompleto().toUpperCase().replaceAll(" ", "");
        String nomeArquivo = dataHora + "-" + nomeFormatado + ".TXT";
        File diretorio = new File("petsCadastrados");

        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
        File arquivo = new File(diretorio, nomeArquivo);
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo))) {
                writer.println("1 - "+ pet.getNomeCompleto());
                writer.println("2 - " + pet.getTipoPet());
                writer.println("3 - " + pet.getSexoPet());
                writer.println("4 - " + pet.getEndereco());

                if (pet.getIdade() == 0) {
                    writer.println("5 - Não informado");
                } else {
                    writer.println("5 - " + pet.getIdade() + " anos");
                }
                if (pet.getPeso() == 0.0) {
                    writer.println("6 - Não informado");
                } else {
                    writer.println("6 - " + pet.getPeso() + " kg");
                }
                writer.println("7 - " + pet.getRaca());
            System.out.println("Pet salvo com sucesso no arquivo: " + arquivo.getPath());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o pet no arquivo: " + e.getMessage());
        }

    }
    public List<Pet> carregarTodosOsPets(){
        List<Pet> listaDePets = new ArrayList<>();
        File file = new File("petsCadastrados");
        if (!file.exists() || file.listFiles() == null){
            return listaDePets;
        }
        for (File file1 : file.listFiles()) {
            if (file1.getName().endsWith(".TXT")) {
                try{
                    List<String> linhas = Files.readAllLines(file1.toPath());
                    if (linhas.size() < 7) {
                        System.out.println("Arquivo malformatado: " + file1.getName());
                        continue;
                    }
                    Pet pet = new Pet();
                    pet.setNomeCompleto(linhas.get(0).substring(4).trim());
                    String tipoTxt = linhas.get(1).substring(4).trim().toUpperCase();
                    pet.setTipoPet(TipoPet.valueOf(tipoTxt));
                    String sexoTxt = linhas.get(2).substring(4).trim().toUpperCase();
                    pet.setSexoPet(SexoPet.valueOf(sexoTxt));
                    pet.setEndereco(linhas.get(3).substring(4).trim());

                    String idadeTxt = linhas.get(4).substring(4).trim();
                    if(idadeTxt.equalsIgnoreCase("Não informado")){
                        pet.setIdade(0.0);
                    }else {
                        pet.setIdade(Double.parseDouble(idadeTxt.replaceAll("[^0-9.]", "")));
                    }

                    String pesoTxt = linhas.get(5).substring(4).trim();
                    if(pesoTxt.equalsIgnoreCase("Não informado")){
                        pet.setPeso(0.0);
                    }else {
                        pet.setPeso(Double.parseDouble(pesoTxt.replaceAll("[^0-9.]", "")));
                    }
                    pet.setRaca(linhas.get(6).substring(4).trim());
                    pet.setArquivoOrigem(file1.getName());
                    listaDePets.add(pet);
                }catch (Exception e){
                    System.out.println("Erro ao ler o arquivo " + file1.getName() + ": " + e.getMessage());

                }
            }
        }return listaDePets;
    }
    public void atualizarPet(Pet petAntigo, Pet petAtual){
        File arquivoAntigo = new File("petsCadastrados", petAntigo.getArquivoOrigem());
        if (arquivoAntigo.exists()) {
            arquivoAntigo.delete();
        }
        salvarPetEmArquivo(petAtual);
    }
    public boolean deletarPet(Pet pet){
        File file = new File("petsCadastrados", pet.getArquivoOrigem());
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }


}
