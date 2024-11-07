import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LerDadosDeTxt {

    public static void main(String[] args) {
        // O caminho do arquivo onde os dados foram armazenados
        String caminhoArquivo = "dados.txt";

        // Chama o método para ler os dados do arquivo
        lerDadosDoArquivo(caminhoArquivo);
    }

    // Método para ler dados do arquivo
    public static void lerDadosDoArquivo(String caminhoArquivo) {
        // Tenta abrir o arquivo e ler seu conteúdo
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            // Lê linha por linha até o final do arquivo
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha); // Exibe a linha lida
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}