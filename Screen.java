import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JFrame {
    public Screen() {
            int comprimento_painel = 900;
            int altura_painel = 600;
            //JFrame painel = new JFrame(); // inicializa o painel / caso tenha o extends então nao é necessario instanciar
            setTitle("Sistema Banco"); // ex instanciado painel.setTitle("SistemaBanco.Banco");
            setVisible(true); // Deixa o painel visivel
            setSize(comprimento_painel, altura_painel); // tamanho da janela
            setDefaultCloseOperation(EXIT_ON_CLOSE); // Finaliza o programa ao fechar a janela
            setResizable(false); // mudar o tamanho da janela
            setLocationRelativeTo(null); // iniciar janela no centro
            setLayout(null);// Resetando config de design

            Color laranja = new Color(252,142,57);
            Color branco = new Color(0xFFFFFF);
            Color azul = new Color(0x00A7EF);

            getContentPane().setBackground(laranja);

            JButton login_b = new JButton("Fazer Login");// instancia do botao
            JButton cadastro_b = new JButton("Fazer Cadastro");

            // design do botao
            int comprimento_botao = 250;
            int altura_botao = 150;
            /* centralizar
                int x = (comprimento_painel - comprimento_botao)/2; 320 NO MEIO
                int y = (altura_painel - altura_botao) / 2 - getInsets().top;
            */
            int x = 155;
            int y = (altura_painel - altura_botao) / 2 - getInsets().top;

            login_b.setBounds(x,y,comprimento_botao,altura_botao);
            cadastro_b.setBounds(x*3,y,comprimento_botao,altura_botao);


            Font arial = new Font("Arial",Font.BOLD,20);
            login_b.setFont(arial);
            cadastro_b.setFont(arial);



            login_b.setForeground(laranja);
            login_b.setBackground(branco);
            cadastro_b.setForeground(laranja);
            cadastro_b.setBackground(branco);

            add(login_b);
            add(cadastro_b);// colocando o botao no painel |



            login_b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new LoginForm();
                }
            });
        cadastro_b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroForm();
            }
        });
            setVisible(true);
    }

//    public static void main(String[] args) {
//        new Screen();
//    }
}


class LoginForm extends JFrame {
        public LoginForm() {
            int comprimento_painel = 800;
            int altura_painel = 500;

            setTitle("Login");
            setSize(comprimento_painel, altura_painel);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);// define oq acontece quando fechar a janela
            setLayout(null);
            setLocationRelativeTo(null);
            setResizable(false);

            Color laranja = new Color(252,142,57);

            getContentPane().setBackground(laranja);

            int xuser = 300;
            int yuser = 100;

            JLabel usuarioLabel = new JLabel("Num conta:");
            usuarioLabel.setBounds(xuser,yuser - 25, 100, 30);
            usuarioLabel.setForeground(Color.white);
            add(usuarioLabel);

            JTextField user = new JTextField();
            user.setBounds(xuser,yuser,180,30);
            add(user);


            JLabel senha_label = new JLabel("Senha:");
            senha_label.setBounds(xuser,yuser + 50, 100, 30);
            senha_label.setForeground(Color.white);
            add(senha_label);

            JPasswordField senha = new JPasswordField();
            senha.setBounds(xuser,yuser + 75,180,30);
            add(senha);

            JButton login = new JButton("Login");
            login.setBounds(xuser,yuser + 115, 100, 30);
            add(login);

            login.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //integrar a parti daqui
                    Banco banco = new Banco("Itau");
                    String caminhoArquivo = "dados.txt";
                    LerDadosDeTxt.lerDadosDoArquivo(caminhoArquivo, banco);

                    String login = user.getText();
                    String pass = senha.getText();
                    int numconta = Integer.parseInt(login);

                    ContaBancaria contaAutenticada = banco.buscarConta(numconta, pass);

                    if (contaAutenticada != null) {
                        dispose();
                        new App(contaAutenticada);
                        //JOptionPane.showMessageDialog(null,"Login realizado com sucesso!", "lOGIN", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        System.out.println(numconta);
                        System.out.println(pass);
                        JOptionPane.showMessageDialog(null,"Login ou senha incorretos.", "erro", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            setVisible(true);
        }
    }
class CadastroForm extends JFrame {
    public CadastroForm() {
        int comprimento_painel = 800;
        int altura_painel = 500;

        int xuser = 250;
        int yuser = 100;

        setTitle("Cadastro");
        setSize(comprimento_painel, altura_painel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);// define oq acontece quando fechar a janela
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        Color laranja = new Color(252,142,57);

        getContentPane().setBackground(laranja);

        JButton fisica = new JButton("Conta Física");// instancia do botao
        JButton juridica = new JButton("Conta Jurídica");

        // design do botao
        int comprimento_botao = 250;
        int altura_botao = 150;

        int x = 115;
        int y = 155;


        fisica.setBounds(x,y,comprimento_botao,altura_botao);
        juridica.setBounds(x*4,y,comprimento_botao,altura_botao);

        Font arial = new Font("Arial",Font.BOLD,20);
        fisica.setFont(arial);
        juridica.setFont(arial);

        fisica.setForeground(laranja);fisica.setBackground(Color.white);
        juridica.setForeground(laranja);juridica.setBackground(Color.white);

        add(fisica);
        add(juridica);

        fisica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FisicaForm();
                dispose();

            }
        });
        juridica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JuridicaForm();
                dispose();
            }
        });

        setVisible(true);

    }
}
class FisicaForm extends JFrame {
    public FisicaForm() {
        int comprimento_painel = 800;
        int altura_painel = 500;

        int xuser = 250;
        int yuser = 100;

        setTitle("Cadastro");
        setSize(comprimento_painel, altura_painel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);// define oq acontece quando fechar a janela
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        Color laranja = new Color(252,142,57);

        getContentPane().setBackground(laranja);

        //forms
        JLabel usuarioLabel = new JLabel("Nome:");
        usuarioLabel.setBounds(xuser,yuser - 25, 100, 30);
        usuarioLabel.setForeground(Color.white);

        JTextField user = new JTextField();
        user.setBounds(xuser,yuser,180,30);


        JLabel cpf_label = new JLabel("CPF:");
        cpf_label.setBounds(xuser,yuser + 60 - 25, 100, 30);
        cpf_label.setForeground(Color.white);

        JTextField cpf = new JTextField();
        cpf.setBounds(xuser,yuser + 60,180,30);


        JLabel senha_label = new JLabel("Senha:");
        senha_label.setBounds(xuser,yuser + 120 - 25, 100, 30);
        senha_label.setForeground(Color.white);

        JPasswordField senha = new JPasswordField();
        senha.setBounds(xuser,yuser + 120,180,30);

        JLabel saldo_label = new JLabel("Saldo:");
        saldo_label.setBounds(xuser,yuser + 180- 25, 100, 30);
        saldo_label.setForeground(Color.white);

        JTextField saldo = new JTextField();
        saldo.setBounds(xuser,yuser + 180,180,30);

        add(usuarioLabel);
        add(user);
        add(cpf_label);
        add(cpf);
        add(senha_label);
        add(senha);
        add(saldo_label);
        add(saldo);

        JButton cadastrar = new JButton("Cadastrar");
        cadastrar.setBounds(xuser,yuser + 220, 100, 30);
        add(cadastrar);

        cadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome_value = user.getText();
                String cpf_value = cpf.getText();
                String senha_value = senha.getText();
                String tipoconta = "F";
                double saldo_value = Float.parseFloat((saldo.getText()));
                int num_conta = (int) (Math.random() * 10000);
                ArmazenarDadosDeVariavel.salvarDadosNoArquivo("dados.txt", tipoconta + " | " + num_conta + " | " + senha_value + " | " + saldo_value + " | " + nome_value + " | " + cpf_value);
                JOptionPane.showMessageDialog(null,"Cadastro Concluido, numero da sua conta:" + num_conta , "Alerta", JOptionPane.WARNING_MESSAGE);

            }
        });


        setVisible(true);
    }
}
class JuridicaForm extends JFrame {
    public JuridicaForm() {
        int comprimento_painel = 800;
        int altura_painel = 500;

        int xuser = 250;
        int yuser = 100;

        setTitle("Cadastro");
        setSize(comprimento_painel, altura_painel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);// define oq acontece quando fechar a janela
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        Color laranja = new Color(252,142,57);

        getContentPane().setBackground(laranja);

        //forms
        JLabel usuarioLabel = new JLabel("Nome:");
        usuarioLabel.setBounds(xuser,yuser - 25, 100, 30);
        usuarioLabel.setForeground(Color.white);

        JTextField user = new JTextField();
        user.setBounds(xuser,yuser,180,30);

        JLabel cnpj_label = new JLabel("CNPJ:");
        cnpj_label.setBounds(xuser,yuser + 60 - 25, 100, 30);
        cnpj_label.setForeground(Color.white);

        JTextField cnpj = new JTextField();
        cnpj.setBounds(xuser,yuser + 60,180,30);


        JLabel senha_label = new JLabel("Senha:");
        senha_label.setBounds(xuser,yuser + 120 - 25, 100, 30);
        senha_label.setForeground(Color.white);

        JPasswordField senha = new JPasswordField();
        senha.setBounds(xuser,yuser + 120,180,30);


        JLabel saldo_label = new JLabel("Saldo:");
        saldo_label.setBounds(xuser,yuser + 180- 25, 100, 30);
        saldo_label.setForeground(Color.white);

        JTextField saldo = new JTextField();
        saldo.setBounds(xuser,yuser + 180,180,30);

        add(usuarioLabel);
        add(user);
        add(cnpj_label);
        add(cnpj);
        add(senha_label);
        add(senha);
        add(saldo_label);
        add(saldo);

        JButton cadastrar = new JButton("Cadastrar");
        cadastrar.setBounds(xuser,yuser + 220, 100, 30);
        add(cadastrar);

        cadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome_value = user.getText();
                String cnpj_value = cnpj.getText();
                String senha_value = senha.getText();
                String tipoconta = "J";
                float saldo_value = Float.parseFloat((saldo.getText()));
                int num_conta = (int) (Math.random() * 10000);
                ArmazenarDadosDeVariavel.salvarDadosNoArquivo("dados.txt", tipoconta + " | " + num_conta + " | " + senha_value + " | " + saldo_value + " | " + nome_value + " | " + cnpj_value);
                JOptionPane.showMessageDialog(null,"Cadastro Concluido, numero da sua conta:" + num_conta , "Alerta", JOptionPane.WARNING_MESSAGE);

            }
        });

        setVisible(true);
    }
}
class App extends JFrame{
    private ContaBancaria conta;

    public App(ContaBancaria conta) {
        this.conta = conta;

        int comprimento_painel = 910;
        int altura_painel = 610;

        setTitle("App");
        setVisible(true);
        setSize(comprimento_painel, altura_painel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        Color laranja = new Color(252, 142, 57);

        getContentPane().setBackground(laranja);

        String nome = conta.getNome();
        Double saldo = conta.getSaldo();

        JLabel nomeLabel = new JLabel("Bem-vindo(a), " + nome + "!");
        nomeLabel.setBounds(50, 50, 300, 30);
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nomeLabel.setForeground(Color.WHITE);
        add(nomeLabel);

        JLabel saldoLabel = new JLabel("Saldo: " + saldo + "!");
        saldoLabel.setBounds(50, 85, 300, 30);
        saldoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        saldoLabel.setForeground(Color.white);
        add(saldoLabel);
        // pagina dps de logar, identificar o tipo da conta e escrever no dados.txt quando sacar e depositar.
        //JOptionPane.showMessageDialog(null,conta.getNome(), "ola", JOptionPane.WARNING_MESSAGE);
    }
}