import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
}
