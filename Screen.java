import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JFrame {
    public Screen() {
        int comprimento_painel = 900;
        int altura_painel = 600;

        setTitle("SistemaBanco.Banco");
        setSize(comprimento_painel, altura_painel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton login_b = new JButton("Fazer Login");
        JButton cadastro_b = new JButton("Fazer Cadastro");

        int comprimento_botao = 250;
        int altura_botao = 150;
        int x = 155;
        int y = (altura_painel - altura_botao) / 2 - getInsets().top;

        login_b.setBounds(x, y, comprimento_botao, altura_botao);
        cadastro_b.setBounds(x * 3, y, comprimento_botao, altura_botao);

        Font arial = new Font("Arial", Font.BOLD, 20);
        login_b.setFont(arial);
        cadastro_b.setFont(arial);

        Color laranja = new Color(0xF15E00);
        Color branco = new Color(0xFFFFFF);

        login_b.setForeground(laranja);
        login_b.setBackground(branco);
        cadastro_b.setForeground(laranja);
        cadastro_b.setBackground(branco);

        add(login_b);
        add(cadastro_b);

        login_b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginForm();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Screen();
    }
}

class LoginForm extends JFrame {
    public LoginForm() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Usuário:"));
        JTextField userField = new JTextField();
        add(userField);

        add(new JLabel("Senha:"));
        JPasswordField passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Entrar");
        add(loginButton);
        
        setVisible(true);
    }
}
