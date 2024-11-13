import java.util.ArrayList;

public class Banco {
    String nome;
    ArrayList<ContaBancaria> contas;

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

    public ContaBancaria buscarConta(int numeroDaConta, String senha) {
        for (ContaBancaria conta : contas) {
            if (conta.getNumeroDaConta() == numeroDaConta && conta.validarSenha(senha)) {
                return conta;
            }
        }
        return null;
    }
    public ContaBancaria buscarContaPIX(int numeroDaConta) {
        for (ContaBancaria conta : contas) {
            if (conta.getNumeroDaConta() == numeroDaConta) {
                return conta;
            }
        }
        return null;
    }

    // Método PIX para transferência
    public boolean realizarPix(int numeroContaOrigem, String senha, int numeroContaDestino, double valor) {
        // Encontrar conta de origem
        ContaBancaria contaOrigem = buscarContaPIX(numeroContaOrigem);
        if (contaOrigem == null) {
            System.out.println("Conta de origem não encontrada ou senha incorreta.");
            return false;
        }

        // Encontrar conta de destino
        ContaBancaria contaDestino = null;
        for (ContaBancaria conta : contas) {
            if (conta.getNumeroDaConta() == numeroContaDestino) {
                contaDestino = conta;
                break;
            }
        }

        if (contaDestino == null) {
            System.out.println("Conta de destino não encontrada.");
            return false;
        }

        // Validar saldo e realizar transferência
        if (contaOrigem.sacar(valor)) {
            contaDestino.depositar(valor);
            System.out.println("PIX realizado com sucesso!");
            return true;
        } else {
            System.out.println("Saldo insuficiente para realizar o PIX.");
            return false;
        }
    }
}


abstract class ContaBancaria {
    private int numeroDaConta;
    private String senha;
    protected double saldo;
    private String nome;
    private String cpf;

    public ContaBancaria(int numeroDaConta, String senha, double saldo, String nome, String cpf) {
        this.numeroDaConta = numeroDaConta;
        this.saldo = saldo;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public int getNumeroDaConta() { return numeroDaConta; }
    public String getNome() { return nome; }
    public double getSaldo() { return saldo; }
    public String getSenha() {return senha;}
    public String getCpf() {return cpf;}

    public boolean validarSenha(String senha) {
        return this.senha.equals(senha);
    }


    public boolean depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            return true;
        }
        return false;
    }

    public abstract boolean sacar(double valor);
    public abstract String getTipo();
}

abstract class ContaBancariaPF extends ContaBancaria {
    public ContaBancariaPF(int numeroDaConta, String senha, double saldo, String nome, String cpf) {
        super(numeroDaConta, senha, saldo, nome, cpf);
    }
    public abstract String getTipo();
}

abstract class ContaBancariaPJ extends ContaBancaria {
    public ContaBancariaPJ(int numeroDaConta, String senha, double saldo, String nome, String cnpj) {
        super(numeroDaConta, senha, saldo, nome, cnpj);
    }
}

class ContaCorrentePF extends ContaBancariaPF {
    public ContaCorrentePF(int numeroDaConta, String senha, double saldo, String nome, String cpf) {
        super(numeroDaConta, senha, saldo, nome, cpf);
    }
    @Override
    public String getTipo() {
        return "Tipo da Conta PF: Corrente";
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            String dados = "F" + " | " + getNumeroDaConta() + " | " + getSenha() + " | " + saldo + " | " + getNome() + " | " + getCpf() + " | " + "C";
            ArmazenarDadosDeVariavel.alterarDadosNoArquivo("dados.txt",getNumeroDaConta(),dados);
            return true;
        }
        return false;
    }
    @Override
    public boolean depositar(double valor) {
        if (valor > 0){
            saldo += valor;
            String dados = "F" + " | " + getNumeroDaConta() + " | " + getSenha() + " | " + saldo + " | " + getNome() + " | " + getCpf() + " | " + "C";
            ArmazenarDadosDeVariavel.alterarDadosNoArquivo("dados.txt",getNumeroDaConta(),dados);
            return true;
        }
        return false;
    }
}

class ContaPoupancaPF extends ContaBancariaPF {
    public ContaPoupancaPF(int numeroDaConta, String senha, double saldo, String nome, String cpf) {
        super(numeroDaConta, senha, saldo, nome, cpf);
    }

    @Override
    public String getTipo() {
        return "Tipo da Conta PF: Poupança";
    }

    @Override
    public boolean sacar(double valor) {
        double saldoMinimo = 50.0;
        if (valor <= saldo && valor >= saldoMinimo) {
            saldo -= valor;
            String dados = "F" + " | " + getNumeroDaConta() + " | " + getSenha() + " | " + saldo + " | " + getNome() + " | " + getCpf() + " | " + "P";
            ArmazenarDadosDeVariavel.alterarDadosNoArquivo("dados.txt",getNumeroDaConta(),dados);
            return true;
        }
        return false;
    }
    @Override
    public boolean depositar(double valor) {
        if (valor > 0){
            saldo += valor;
            //System.out.println(saldo);
            String dados = "F" + " | " + getNumeroDaConta() + " | " + getSenha() + " | " + saldo + " | " + getNome() + " | " + getCpf() + " | " + "P";
            ArmazenarDadosDeVariavel.alterarDadosNoArquivo("dados.txt",getNumeroDaConta(),dados);
            return true;
        }
        return false;
    }

}

class ContaCorrentePJ extends ContaBancariaPJ {
    private static final double TAXA_SAQUE = 5.0;
    private static final double TAXA_DEPOSITO = 2.0;

    public ContaCorrentePJ(int numeroDaConta, String senha, double saldo, String nome, String cnpj) {
        super(numeroDaConta, senha, saldo, nome, cnpj);
    }

    @Override
    public boolean sacar(double valor) {
        double valorTotal = valor + TAXA_SAQUE;
        if (valorTotal <= saldo) {
            saldo -= valorTotal;
            String dados = "J" + " | " + getNumeroDaConta() + " | " + getSenha() + " | " + saldo + " | " + getNome() + " | " + getCpf() + " | " + "C";
            ArmazenarDadosDeVariavel.alterarDadosNoArquivo("dados.txt",getNumeroDaConta(),dados);
            return true;
        }
        return false;
    }


    @Override
    public String getTipo() {
        return "Tipo da Conta PJ: Corrente";
    }

    @Override
    public boolean depositar(double valor) {
        if (valor > TAXA_DEPOSITO) {
            saldo += (valor - TAXA_DEPOSITO);
            String dados = "J" + " | " + getNumeroDaConta() + " | " + getSenha() + " | " + saldo + " | " + getNome() + " | " + getCpf() + " | " + "C";
            ArmazenarDadosDeVariavel.alterarDadosNoArquivo("dados.txt",getNumeroDaConta(),dados);
            return true;
        }
        return false;
    }
}

class ContaPoupancaPJ extends ContaBancariaPJ {
    private int saquesRealizados;
    private static final int LIMITE_SAQUES = 3;
    private static final double LIMITE_DEPOSITO = 10000.0;

    public ContaPoupancaPJ(int numeroDaConta, String senha, double saldo, String nome, String cnpj) {
        super(numeroDaConta, senha, saldo, nome, cnpj);
        this.saquesRealizados = 0;
    }
    @Override
    public String getTipo() {
        return "Tipo da Conta PJ: Poupança";
    }

    @Override
    public boolean sacar(double valor) {
        if (saquesRealizados < LIMITE_SAQUES && valor <= saldo) {
            saldo -= valor;
            saquesRealizados++;
            String dados = "J" + " | " + getNumeroDaConta() + " | " + getSenha() + " | " + saldo + " | " + getNome() + " | " + getCpf() + " | " + "P";
            ArmazenarDadosDeVariavel.alterarDadosNoArquivo("dados.txt",getNumeroDaConta(),dados);
            return true;
        }
        return false;
    }

    @Override
    public boolean depositar(double valor) {
        if (valor > 0 && valor <= LIMITE_DEPOSITO) {
            saldo += valor;
            String dados = "J" + " | " + getNumeroDaConta() + " | " + getSenha() + " | " + saldo + " | " + getNome() + " | " + getCpf() + " | " + "P";
            ArmazenarDadosDeVariavel.alterarDadosNoArquivo("dados.txt",getNumeroDaConta(),dados);
            return true;
        }
        return false;
    }
}



