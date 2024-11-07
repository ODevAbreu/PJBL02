import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ArmazenarDadosDeVariavel {

    public static void main(String[] args) {

        String nome = "Nome: Prof pedro ";
        String senha = "Senha: 123" ;




        String informacoes = nome + " | " + senha  ;

        // Caminho do arquivo onde os dados serão armazenados
        String caminhoArquivo = "dados.txt";

        // Chamando o método para salvar os dados no arquivo
        salvarDadosNoArquivo(caminhoArquivo, informacoes);
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
