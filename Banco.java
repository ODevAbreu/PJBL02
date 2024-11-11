import java.util.ArrayList;


public class Banco {
    String nome;
    ArrayList<ContaBancaria> contas;

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<ContaBancaria>();
    }
    public ContaBancaria buscarConta(int numeroDaConta, String senha) {
        for (ContaBancaria conta : contas) {
            if (conta.getNumeroDaConta() == numeroDaConta && conta.validarSenha(senha)) {
                return conta;
            }
        }
        return null;
    }
}

abstract class ContaBancaria{
    private int numeroDaConta;
    private String senha;
    protected double saldo;
    private String nome;
    private String cpf;


    public ContaBancaria( int numeroDaConta , String senha , double saldo, String nome, String cpf ){
        this.numeroDaConta =  numeroDaConta;
        this.saldo = saldo ;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        // tipo conta para verificar se a conta é pj ou pf
    }
    public int getNumeroDaConta() {return numeroDaConta;}
    public String getNome() {return nome;}
    public double getSaldo() {return saldo;}
    public boolean validarSenha(String senha) {
        return this.senha.equals(senha);
    }

    public abstract boolean sacar(double valor);
}

class ContaBancariaPF extends ContaBancaria{
    private String cpf;

    public ContaBancariaPF(int numeroDaConta, String senha, double saldo, String nome, String cpf ) {
        super(numeroDaConta, senha, saldo, nome, cpf);
    }

    @Override
    public boolean sacar(double valor) {
        return false;
    }
}
class ContaBancariaPJ extends ContaBancaria{
    private String cnpj;

    public ContaBancariaPJ(int numeroDaConta, String senha, double saldo, String nome, String cnpj) {
        super(numeroDaConta, senha, saldo, nome, cnpj);
    }

    @Override
    public boolean sacar(double valor) {
        return false;
    }
}
class ContaCorrentePF extends ContaBancariaPF{

    public ContaCorrentePF(int numeroDaConta, String senha, double saldo, String nome, String cpf) {
        super(numeroDaConta, senha, saldo, nome, cpf);
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
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
    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        }
        return false;
    }
}
class ContaCorrentePJ extends ContaBancariaPJ{


    public ContaCorrentePJ(int numeroDaConta, String senha, double saldo, String nome, String cpf) {
        super(numeroDaConta, senha, saldo, nome, cpf);
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        }
        return false;
    }
}

class ContaPoupancaPJ extends ContaBancariaPJ{
    public ContaPoupancaPJ(int numeroDaConta, String senha, double saldo, String nome, String cpf) {
        super(numeroDaConta, senha, saldo, nome, cpf);
    }

    @Override
    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        }
        return false;
    }
}

