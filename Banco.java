import java.util.ArrayList;
import java.util.Scanner;

public class Banco {
    String nome;
    ArrayList<Conta> contas;

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<Conta>();
    }

    public void addConta(Conta conta) {
        this.contas.add(conta);
    }

    public void criarConta(int numero, String senha, double saldo) {
        Conta c = new ContaCorrente(numero, senha, saldo);
        addConta(c);
    }

    public void sacar(int numero, String senha, double valor) {
        Conta conta = buscarConta(numero);
        if (conta != null && conta.senhaValida(senha)) {
            conta.retirar(valor);
        } else {
            System.out.println("Saque não autorizado.");
        }
    }

    public void depositar(int numero, double valor) {
        Conta conta = buscarConta(numero);
        if (conta != null) {
            conta.depositar(valor);
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    public Conta buscarConta(int numero) {
        for (Conta conta : contas) {
            if (conta.getNumero() == numero) {
                return conta;
            }
        }
        System.out.println("Conta não encontrada.");
        return null;
    }
}

abstract class Conta {
    private int numeroDaConta;
    private String senha;
    private double saldo;

    public Conta(int numeroDaConta, String senha, double saldo) {
        this.numeroDaConta = numeroDaConta;
        this.senha = senha;
        this.saldo = saldo;
    }

    public int getNumero() {
        return numeroDaConta;
    }

    public boolean senhaValida(String senha) {
        return this.senha.equals(senha);
    }

    public void retirar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque realizado: " + valor);
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito realizado: " + valor);
        } else {
            System.out.println("Valor de depósito deve ser maior que zero.");
        }
    }

    public double consultarSaldo() {
        return saldo;
    }
}

class ContaCorrente extends Conta {
    public ContaCorrente(int numeroDaConta, String senha, double saldo) {
        super(numeroDaConta, senha, saldo);
    }
}

class ContaPoupanca extends Conta {
    public ContaPoupanca(int numeroDaConta, String senha, double saldo) {
        super(numeroDaConta, senha, saldo);
    }
}

class Cliente {
    private String nome;
    private String cpf;
    private String email;

    public Cliente(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }
}


class ExcecaoSenhaInvalida extends Exception {}

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Banco banco = new Banco("Itau");

        System.out.println(banco.nome);

        boolean menu = true;
        while (menu) {
            System.out.println("1 - sacar");
            System.out.println("2 - criar conta");
            System.out.println("3 - depositar");
            System.out.println("4 - consultar saldo");
            System.out.println("5 - Listar Contas");
            System.out.println("6 - Sair");
            int valor_menu = in.nextInt();

            switch (valor_menu) {
                case 1:
                    System.out.println("Digite o numero da conta:");
                    int numero = in.nextInt();
                    System.out.println("Digite a Senha:");
                    String senha = in.next();
                    System.out.println("Digite o valor para saque:");
                    double valorSaque = in.nextDouble();
                    banco.sacar(numero, senha, valorSaque);
                    break;
                case 2:
                    System.out.println("Digite o numero da conta:");
                    numero = in.nextInt();
                    System.out.println("Digite a Senha:");
                    senha = in.next();
                    System.out.println("Digite o saldo:");
                    double saldo = in.nextDouble();
                    banco.criarConta(numero, senha, saldo);
                    break;
                case 3:
                    System.out.println("Digite o numero da conta:");
                    numero = in.nextInt();
                    System.out.println("Digite o valor para depósito:");
                    double valorDeposito = in.nextDouble();
                    banco.depositar(numero, valorDeposito);
                    break;
                case 4:
                    System.out.println("Digite o numero da conta:");
                    numero = in.nextInt();
                    Conta contaConsulta = banco.buscarConta(numero);
                    if (contaConsulta != null) {
                        System.out.println("Saldo atual: R$ " + contaConsulta.consultarSaldo());
                    }
                    break;
                case 5:
                    System.out.println("Contas cadastradas:");
                    for (Conta conta : banco.contas) {
                        System.out.println("Conta: " + conta.getNumero());
                    }
                    break;
                case 6:
                    menu = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        in.close();
    }
}