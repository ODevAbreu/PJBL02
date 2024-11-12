import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LerDadosDeTxt {

    public static void lerDadosDoArquivo(String caminhoArquivo, Banco banco) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split("\\|");

                if (partes.length >= 6) {

                    String tipoConta = partes[0].trim();
                    int numConta = Integer.parseInt(partes[1].trim());
                    String senha = partes[2].trim();
                    double saldo = Double.parseDouble(partes[3].trim());
                    String nome = partes[4].trim();
                    String cpfOuCnpj = partes[5].trim();
                    String P_ou_C = partes[6].trim();

                    if ("F".equals(tipoConta)) {
                        if (P_ou_C == String.valueOf('C')) {
                            ContaCorrentePF conta = new ContaCorrentePF(numConta, senha, saldo, nome, cpfOuCnpj);
                            banco.contas.add(conta);
                        }else{
                            ContaPoupancaPF conta = new ContaPoupancaPF(numConta, senha, saldo, nome, cpfOuCnpj);
                            banco.contas.add(conta);
                        }

                    } else if ("J".equals(tipoConta)) {
                        if (P_ou_C == String.valueOf('C')) {
                            ContaCorrentePJ conta = new ContaCorrentePJ(numConta, senha, saldo, nome, cpfOuCnpj);
                            banco.contas.add(conta);
                        }else{
                            ContaPoupancaPJ conta = new ContaPoupancaPJ(numConta, senha, saldo, nome, cpfOuCnpj);
                            banco.contas.add(conta);
                        }
                    }

                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
