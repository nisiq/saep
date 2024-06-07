package interfaces;

import componentes.Botao;
import componentes.Janela;
import componentes.Table;
import entities.LoginIn;
import entities.Turmasx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Principal extends JFrame {

    private int idProfessorLogado;

    public Principal(int idProfessorLogado) {
        this.idProfessorLogado = idProfessorLogado;

        String nomeProfessor = LoginIn.obterNomeProfessor(idProfessorLogado);

        Janela janela = new Janela("Minhas Turmas");
        janela.setLayout(new BorderLayout());

        Table table = new Table();

        Botao btnAdicionar = new Botao("Cadastrar nova turma");
        Botao btnRemover = new Botao("Remover turma");
        Botao btnVisualizar = new Botao("Visualizar turma");
        Botao btnLogout = new Botao("Sair");

        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TurmaCad turmaCad = new TurmaCad(idProfessorLogado);
                turmaCad.setVisible(true);
                janela.dispose();
            }
        });

        btnVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String nomeTurma = (String) table.getValueAt(selectedRow, 0);
                    int idTurma = obterIdTurma(nomeTurma);
                    if (idTurma != -1) {
                        AtividadeCad atividadeCad = new AtividadeCad(idProfessorLogado, idTurma);
                        atividadeCad.setVisible(true);
                        janela.dispose();
                    } else {
                        JOptionPane.showMessageDialog(janela, "Erro ao obter o ID da turma.");
                    }
                } else {
                    JOptionPane.showMessageDialog(janela, "Selecione uma turma para visualizar as atividades.");
                }
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);
                janela.dispose();
            }
        });

        JLabel labelNomeProfessor = new JLabel("Professor: " + nomeProfessor);
        janela.add(labelNomeProfessor, BorderLayout.NORTH);

        janela.add(table, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnLogout);
        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnRemover);
        buttonPanel.add(btnVisualizar);

        janela.add(buttonPanel, BorderLayout.SOUTH);

        List<Object[]> turmaData = Turmasx.obterTurmas(idProfessorLogado);
        table.carregarDados(turmaData);

        janela.setPreferredSize(new Dimension(600, 400));
        janela.setLocationRelativeTo(null);
        janela.pack();
        janela.setVisible(true);
    }

    private int obterIdTurma(String nomeTurma) {
        return Turmasx.obterIdTurmaPeloNome(nomeTurma);
    }

    public void setVisible(boolean b) {
        super.setVisible(b);
    }
}
