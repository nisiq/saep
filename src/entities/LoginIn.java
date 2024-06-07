package entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginIn {

    public static int validarLogin(String email, String senha) {
        try (Connection connection = database.conectar()) {
            String queryClientes = "SELECT id FROM professor WHERE email = ? AND senha = ?";
            try (PreparedStatement preparedStatementClientes = connection.prepareStatement(queryClientes)) {
                preparedStatementClientes.setString(1, email);
                preparedStatementClientes.setString(2, senha);

                try (ResultSet resultSetClientes = preparedStatementClientes.executeQuery()) {
                    if (resultSetClientes.next()) {
                        return resultSetClientes.getInt("id");
                    }
                }
            }
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String obterNomeProfessor(int idProfessor) {
        String nomeProfessor = null;
        try (Connection connection = database.conectar()) {
            String query = "SELECT nome FROM professor WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, idProfessor);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        nomeProfessor = resultSet.getString("nome");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nomeProfessor;
    }
}

