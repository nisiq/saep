package interfaces;

import componentes.Botao;
import componentes.Janela;
import componentes.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JFrame {

    private final int idProfessorLogado;

    public Principal(int idProfessor) {
        this.idProfessorLogado = idProfessor;

        Janela janela = new Janela("Minhas Turmas");
        janela.setLayout(new BorderLayout());

        Table table = new Table();

        Botao btnAdicionar = new Botao("Cadastrar nova turma");
        Botao btnRemover = new Botao("Remover turma");
        Botao btnVisualizar = new Botao("Visualizar turma");

        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TurmaCad turmaCad = new TurmaCad(idProfessorLogado);
                turmaCad.setVisible(true);
            }
        });

        janela.add(table, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnRemover);
        janela.add(buttonPanel, BorderLayout.SOUTH);


        janela.setPreferredSize(new Dimension(600, 400));
        janela.setLocationRelativeTo(null);
        janela.pack();
        janela.setVisible(true);
    }

        // Adicione os botões à janela conforme necessário

    public void setVisible(boolean b) {
        super.setVisible(b);
    }
}
