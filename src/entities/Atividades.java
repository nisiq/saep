package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Atividades {

    public static List<Object[]> obterAtividades(int idTurma) {
        List<Object[]> atividades = new ArrayList<>();
        try (Connection conn = database.conectar()) {
            String CONSULTAR = "SELECT nome FROM atividade WHERE turma_id = ?";
            try (PreparedStatement consultar = conn.prepareStatement(CONSULTAR)) {
                consultar.setInt(1, idTurma);
                ResultSet resultado = consultar.executeQuery();
                while (resultado.next()) {
                    String nome = resultado.getString("nome");
                    Object[] linha = {nome};
                    atividades.add(linha);
                }
                return atividades;
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Erro ao consultar atividades no banco de dados");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao obter conexão com banco de dados");
        }
        return atividades;
    }

    public static void cadastrarAtividade(String titulo, int idTurma) throws SQLException {
        try (Connection conn = database.conectar()) {
            String INSERIR = "INSERT INTO atividade (nome, turma_id) VALUES (?, ?)";
            try (PreparedStatement salvar = conn.prepareStatement(INSERIR)) {
                salvar.setString(1, titulo);
                salvar.setInt(2, idTurma);
                salvar.executeUpdate();
            }
        }
    }

    public static boolean removerAtividade(String titulo, int idTurma) throws SQLException {
        try (Connection conn = database.conectar()) {
            String REMOVER = "DELETE FROM atividade WHERE nome = ? AND turma_id = ?";
            try (PreparedStatement remover = conn.prepareStatement(REMOVER)) {
                remover.setString(1, titulo);
                remover.setInt(2, idTurma);
                remover.executeUpdate();
                System.out.println("Produto removido com sucesso");

                return true;
            } catch (SQLException e){
                e.printStackTrace();
                System.err.println("Erro ao remover o produto do banco de dados");
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.err.println("Erro ao obter conexão com o banco de dados");
        }
        return false;
    }
}
