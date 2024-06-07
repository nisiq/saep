package interfaces;

import componentes.Botao;
import componentes.CampoSenha;
import componentes.CampoTexto;
import componentes.Janela;
import entities.LoginIn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    public void mostrarJanela() {
        Janela janela = new Janela("Login");
        janela.setSize(600, 400);
        janela.setLayout(null);

        JLabel labelUser = new JLabel("Email");
        labelUser.setBounds(400, 70, 100, 30);

        CampoTexto user = new CampoTexto();
        user.setBounds(400, 100, 100, 30);

        JLabel labelSenha = new JLabel("Senha: ");
        labelSenha.setBounds(400, 170, 100, 30);

        CampoSenha senha = new CampoSenha();
        senha.setBounds(400, 200, 100, 30);

        Botao btnLogin = new Botao("Login");
        btnLogin.setBounds(400, 300, 100, 30);

        String imagePath = "src/Imagens/logogestao.png";
        ImageIcon icon = new ImageIcon(imagePath);

        JLabel label = new JLabel(icon);
        label.setBounds(40, -10, icon.getIconWidth(), icon.getIconHeight());

        janela.add(label);
        janela.add(labelUser);
        janela.add(user);
        janela.add(labelSenha);
        janela.add(senha);
        janela.add(btnLogin);

        janela.setVisible(true);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = user.getText();
                String senhaDigitada = new String(senha.getPassword());

                int idUsuario = LoginIn.validarLogin(usuario, senhaDigitada);
                if (idUsuario != -1) {
                    JOptionPane.showMessageDialog(janela, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    int idProfessorLogado = idUsuario;

                    Principal principal = new Principal(idProfessorLogado); // Passe o ID do usuário logado para o construtor de Principal
                    principal.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(janela, "Usuário ou senha incorretos", "Erro", JOptionPane.ERROR_MESSAGE);
                    System.out.println("erro");
                }
            }
        });
    }

    public void setVisible(boolean b) {
    }
}
