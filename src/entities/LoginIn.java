package entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginIn {

    public static int validarLogin(String usuario, String senha) {
        try (Connection connection = database.conectar()) {
            String queryClientes = "SELECT id FROM professores WHERE usuario = ? AND senha = ?";
            try (PreparedStatement preparedStatementClientes = connection.prepareStatement(queryClientes)) {
                preparedStatementClientes.setString(1, usuario);
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
}
