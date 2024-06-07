package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Turmasx {
    public static boolean cadastrarTurma(String nome, int idProfessor) throws SQLException {
        try (Connection conn = database.conectar()) {
            String INSERIR = "INSERT INTO turma (nome, professor_id) VALUES (?, ?)";
            try (PreparedStatement salvar = conn.prepareStatement(INSERIR)) {
                salvar.setString(1, nome);
                salvar.setInt(2, idProfessor);
                salvar.executeUpdate();
                System.out.println("Turma cadastrada com sucesso!!!");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erro ao salvar a turma no banco de dados");
            }
            return false;
        }
    }

    public static List<Object[]> obterTurmas(int idProfessor) {
        List<Object[]> turma = new ArrayList<>();
        try (Connection conn = database.conectar()) {
            String CONSULTAR = "SELECT nome FROM turma WHERE professor_id = ?";
            try (PreparedStatement consultar = conn.prepareStatement(CONSULTAR)) {
                consultar.setInt(1, idProfessor);
                ResultSet resultado = consultar.executeQuery();
                while (resultado.next()) {
                    String nome = resultado.getString("nome");
                    Object[] linha = {nome};
                    turma.add(linha);
                }
                return turma;
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erro ao consultar a turma no banco de dados");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao obter conexão com o banco de dados");
        }
        return turma;
    }

    public static int obterIdTurmaPeloNome(String nomeTurma) {
        int idTurma = -1;
        try (Connection conn = database.conectar()) {
            String CONSULTAR = "SELECT id FROM turma WHERE nome = ?";
            try (PreparedStatement consultar = conn.prepareStatement(CONSULTAR)) {
                consultar.setString(1, nomeTurma);
                ResultSet resultado = consultar.executeQuery();
                if (resultado.next()) {
                    idTurma = resultado.getInt("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erro ao consultar o ID da turma no banco de dados");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao obter conexão com banco de dados");
        }
        return idTurma;
    }
}
