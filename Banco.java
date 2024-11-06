import java.util.ArrayList;
import java.util.List;

public class Banco {
    String nome;
    ArrayList<Usuario> usuarios;
    ArrayList<Conta> contas;

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<Conta>();
    }
}

class Usuario{
    private String nome;
    private String cpf;
    private String login;
    private String senha;
    private List<Conta> conta;

    public Usuario(String nome, String cpf,String login, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
        this.conta = new ArrayList<>();
    }
}

abstract class Conta{
    private int numeroDaConta;
    private String senha;
    protected double saldo;

    public Conta( int numeroDaConta , String senha , double saldo ){
        this.numeroDaConta =  numeroDaConta;
        this.senha = senha;
        this.saldo = 0.0 ;
    }

    public abstract boolean sacar(double valor);
}

class ContaCorrente extends Conta{
    public ContaCorrente(int numeroDaconta , String senha , double saldo){
        super(numeroDaconta, senha , saldo );
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


class ContaPoupanca extends Conta{
    public ContaPoupanca(int numeroDaconta , String senha , double saldo ){
        super(numeroDaconta, senha , saldo );
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