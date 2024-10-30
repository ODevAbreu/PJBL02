import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    public Screen(){
        int comprimento_painel = 900;
        int altura_painel = 600;
        //JFrame painel = new JFrame(); // inicializa o painel / caso tenha o extends então nao é necessario instanciar
        setTitle("SistemaBanco.Banco"); // ex instanciado painel.setTitle("SistemaBanco.Banco");
        setVisible(true); // Deixa o painel visivel
        setSize(comprimento_painel, altura_painel); // tamanho da janela
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Finaliza o programa ao fechar a janela

        setResizable(true); // mudar o tamanho da janela
        setLocationRelativeTo(null); // iniciar janela no centro

        setLayout(null);// Resetando config de design
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

        Color vermelho = new Color(0xFF0000);
        Color branco = new Color(0xFFFFFF);
        Color azul = new Color(0x00A7EF);
        Color  laranja = new Color(0xF15E00);


        login_b.setForeground(laranja);
        login_b.setBackground(branco);

        cadastro_b.setForeground(laranja);
        cadastro_b.setBackground(branco);

        add(login_b);
        add(cadastro_b);// colocando o botao no painel |
    }
}