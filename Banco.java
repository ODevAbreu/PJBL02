import java.util.ArrayList;
import java.util.Date;


public class Banco {
    String nome;
    ArrayList<ContaBancaria> contas;

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<ContaBancaria>();
    }
}

abstract class ContaBancaria{
    private int numeroDaConta;
    private String senha;
    protected double saldo;
    private String nome;
    private String cpf;
    private String login;
    private Date DataNasc;

    public ContaBancaria( int numeroDaConta , String senha , double saldo, String nome, String cpf, String login, Date DataNasc ){
        this.numeroDaConta =  numeroDaConta;
        this.senha = senha;
        this.saldo = saldo ;
        this.nome = nome;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
        this.DataNasc = DataNasc;
    }

    public boolean validarSenha(String senha) {
        return this.senha.equals(senha);
    }

    public abstract boolean sacar(double valor);
}

class ContaBancariaPF extends ContaBancaria{
    private String cpf;

    public ContaBancariaPF(int numeroDaConta, String senha, double saldo, String nome, String cpf, String login, Date DataNasc) {
        super(numeroDaConta, senha, saldo, nome, cpf, login, DataNasc);
    }

    @Override
    public boolean sacar(double valor) {
        return false;
    }
}
class ContaBancariaPJ extends ContaBancaria{
    private String cnpj;

    public ContaBancariaPJ(int numeroDaConta, String senha, double saldo, String nome, String cpf, String login, Date DataNasc) {
        super(numeroDaConta, senha, saldo, nome, cpf, login, DataNasc);
    }

    @Override
    public boolean sacar(double valor) {
        return false;
    }
}
class ContaCorrentePF extends ContaBancariaPF{

    public ContaCorrentePF(int numeroDaConta, String senha, double saldo, String nome, String cpf, String login, Date DataNasc) {
        super(numeroDaConta, senha, saldo, nome, cpf, login, DataNasc);
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
}

class ContaPoupancaPF extends ContaBancariaPF {

    public ContaPoupancaPF(int numeroDaConta, String senha, double saldo, String nome, String cpf, String login, Date DataNasc) {
        super(numeroDaConta, senha, saldo, nome, cpf, login, DataNasc);
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


    public ContaCorrentePJ(int numeroDaConta, String senha, double saldo, String nome, String cpf, String login, Date DataNasc) {
        super(numeroDaConta, senha, saldo, nome, cpf, login, DataNasc);
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
}

class ContaPoupancaPJ extends ContaBancariaPJ{
    public ContaPoupancaPJ(int numeroDaConta, String senha, double saldo, String nome, String cpf, String login, Date DataNasc) {
        super(numeroDaConta, senha, saldo, nome, cpf, login, DataNasc);
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