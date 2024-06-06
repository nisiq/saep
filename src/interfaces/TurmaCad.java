package interfaces;

import componentes.Botao;
import componentes.CampoTexto;
import componentes.Janela;
import entities.Turmasx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TurmaCad {
    
    private int idProfessorLogado;
    
    public TurmaCad(int idProfessor){
        this.idProfessorLogado = idProfessor;

        Janela janela = new Janela("Cadastro de Turma");
        janela.setLayout(new BorderLayout());
        Botao btnVoltar = new Botao("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                janela.dispose();
                Principal principal = new Principal(idProfessorLogado);
                principal.setVisible(true);
            }
        });

        CampoTexto nomeTurma = new CampoTexto();
        Botao cadTurma = new Botao("Cadastrar");

        cadTurma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeTurma.getText();

                try {
                    if (Turmasx.cadastrarTurma(nome, idProfessor)){
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

        JPanel panelCampos = new JPanel(new GridLayout(2, 2));
        panelCampos.add(new JLabel("Nome da Turma"));
        panelCampos.add(nomeTurma);

        janela.add(panelCampos, BorderLayout.CENTER);
        janela.add(cadTurma, BorderLayout.SOUTH);
        janela.add(btnVoltar, BorderLayout.NORTH);

        janela.setSize(400, 200);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);

        janela.setVisible(true);
        
    }

    // Método para inserir uma nova turma no banco de dados
    private void inserirTurma(String nome, int idProfessor) {

    }


    public void setVisible(boolean b) {
    }
}
