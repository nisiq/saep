package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Turmasx {
    public static boolean cadastrarTurma(String nome, int idProfessor) throws SQLException {
        try (Connection conn = database.conectar()) {

            String INSERIR = "INSERT INTO turma (nome, id_professor) VALUES (?, ?)";
            try (PreparedStatement salvar = conn.prepareStatement(INSERIR)) {

                salvar.setString(1, nome);
                salvar.setInt(2, idProfessor);

                salvar.executeUpdate();
                System.out.println("Turma cadastrada com sucesso!!!");
                return true;
            } catch (SQLException e){
                e.printStackTrace();
                System.err.println("Erro ao salvar a turma no banco de dados");
            }
            return false;
            }
    }
}
