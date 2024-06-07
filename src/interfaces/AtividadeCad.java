package interfaces;

import componentes.Botao;
import componentes.CampoTexto;
import componentes.Janela;
import entities.Atividades;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class AtividadeCad extends JFrame {
    private final int idProfessorLogado;
    private final int idTurma;

    public AtividadeCad(int idProfessor, int idTurma) {
        this.idProfessorLogado = idProfessor;
        this.idTurma = idTurma;

        Janela janela = new Janela("Atividades da Turma");
        janela.setLayout(new BorderLayout());

        Botao btnVoltar = new Botao("Voltar");


        Botao btnCadastrar = new Botao("Cadastrar Nova Atividade");

        Botao btnRemoverAtividade = new Botao("Concluir Atividade");


        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Principal principal = new Principal(idProfessorLogado);
                principal.setVisible(true);
                janela.dispose();
            }
        });

        List<Object[]> atividades = Atividades.obterAtividades(idTurma);
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Object[] atividade : atividades) {
            listModel.addElement((String) atividade[0]);
        }

        JList<String> listaAtividades = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listaAtividades);
        janela.add(scrollPane, BorderLayout.CENTER);

        JPanel panelCadastro = new JPanel(new GridLayout(2, 2));
        panelCadastro.add(new JLabel("Título da Atividade"));
        CampoTexto nomeAtividade = new CampoTexto();
        panelCadastro.add(nomeAtividade);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeAtividade.getText();
                try {
                    Atividades.cadastrarAtividade(nome, idTurma);
                    listModel.addElement(nome);
                    nomeAtividade.setText("");
                    JOptionPane.showMessageDialog(janela, "Atividade cadastrada com sucesso!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(janela, "Erro ao cadastrar atividade.");
                }
            }
        });

        btnRemoverAtividade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String atividadeSelecionada = listaAtividades.getSelectedValue();
                if (atividadeSelecionada != null) {
                    try {
                        Atividades.removerAtividade(atividadeSelecionada, idTurma);
                        listModel.removeElement(atividadeSelecionada);
                        JOptionPane.showMessageDialog(janela, "Atividade concluida com sucesso!");
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                        JOptionPane.showMessageDialog(janela,"Erro ao concluir atividade");
                    }
                } else {
                    JOptionPane.showMessageDialog(janela, "Selecione uma atividade para concluir...");
                }
            }
        });

        janela.add(panelCadastro, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(btnVoltar);
        buttonPanel.add(btnCadastrar);
        buttonPanel.add(btnRemoverAtividade);

        janela.add(buttonPanel, BorderLayout.SOUTH);

        janela.setSize(600, 400);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }
}
