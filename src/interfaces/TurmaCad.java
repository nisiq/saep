package interfaces;

import componentes.Botao;
import componentes.CampoTexto;
import componentes.Janela;
import entities.Turmasx;
import interfaces.Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TurmaCad {

    private int idProfessorLogado;

    public TurmaCad(int idProfessor) {
        this.idProfessorLogado = idProfessor;

        Janela janela = new Janela("Cadastro de Turma");
        janela.setLayout(null); // Usando layout absoluto

        Botao btnVoltar = new Botao("Voltar");
        btnVoltar.setBounds(20, 20, 100, 30); // Posição e tamanho do botão Voltar
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                janela.dispose();
                Principal principal = new Principal(idProfessorLogado);
                principal.setVisible(true);
            }
        });

        CampoTexto nomeTurma = new CampoTexto();
        nomeTurma.setBounds(150, 20, 200, 30); // Posição e tamanho do campo de texto
        Botao cadTurma = new Botao("Cadastrar");
        cadTurma.setBounds(360, 20, 120, 30); // Posição e tamanho do botão Cadastrar
        cadTurma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeTurma.getText();

                try {
                    if (Turmasx.cadastrarTurma(nome, idProfessor)) {
                        JOptionPane.showMessageDialog(janela, "Produto cadastrado com sucesso!");
                        janela.dispose();
                        Principal principal = new Principal(idProfessorLogado);
                        principal.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(janela, "Erro ao cadastrar o produto.");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        janela.add(btnVoltar);
        janela.add(nomeTurma);
        janela.add(cadTurma);

        janela.setSize(600, 100);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);

    }

    private void inserirTurma(String nome, int idProfessor) {

    }

    public void setVisible(boolean b) {
    }
}
