import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ArmazenarDadosDeVariavel {

    public static void main(String[] args) {

        new Screen();


    }

    // Método para salvar os dados no arquivo
    public static void salvarDadosNoArquivo(String caminhoArquivo, String dados) {
        // Cria o arquivo e escreve os dados no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            writer.write(dados);  // Escreve os dados no arquivo
            writer.newLine();  // Adiciona uma nova linha após os dados
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados no arquivo: " + e.getMessage());
        }
    }

    public static void alterarDadosNoArquivo(String caminhoArquivo, int numConta, String novosDados) {
        try {
            // Lê todas as linhas do arquivo
            List<String> linhas = Files.readAllLines(Paths.get(caminhoArquivo));
            boolean encontrado = false;

            // Cria uma lista para armazenar o conteúdo modificado
            List<String> linhasAlteradas = new ArrayList<>();

            // Itera sobre as linhas e modifica a linha com o número da conta correspondente
            for (String linha : linhas) {
                String[] partes = linha.split("\\|");

                if (partes.length >= 2 && Integer.parseInt(partes[1].trim()) == numConta) {
                    linhasAlteradas.add(novosDados);  // Adiciona os novos dados
                    encontrado = true;
                } else {
                    linhasAlteradas.add(linha);  // Mantém as outras linhas sem alteração
                }
            }

            if (encontrado) {
                // Sobrescreve o arquivo com as alterações
                Files.write(Paths.get(caminhoArquivo), linhasAlteradas, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println("Dados alterados com sucesso!");
            } else {
                System.out.println("Conta não encontrada.");
            }
        } catch (IOException e) {
            System.err.println("Erro ao alterar os dados no arquivo: " + e.getMessage());
        }
    }
}
