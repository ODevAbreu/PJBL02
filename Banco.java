import java.util.ArrayList;
import java.util.List;

public class Banco {
    String nome;
    ArrayList<Usuario> Usuarios;


    public Banco(String nome) {
        this.nome = nome;
        this.Usuarios = new ArrayList<Usuario>();
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
abstract class Conta {
    private String numeroConta;
    private String senha;
    protected double saldo;

    public Conta(String numeroConta, Usuario usuario, String senha) {
        this.numeroConta = numeroConta;
        this.senha = senha;
        this.saldo = 0.0;
    }
}