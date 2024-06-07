package entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {

    public static void main(String[] args) {
            Connection conn = conectar();
            if (conn != null) {
                System.out.println("Conexão bem-sucedida!");
            } else {
                System.out.println("Falha na conexão.");
            }
        }

    private static final String CLASSE_DRIVE = "com.mysql.cj.jdbc.Driver";


    private static final String USUARIO = "root";
    private static final String SENHA = "";

    private static final String URL_SERVIDOR = "jdbc:mysql://localhost:3306/saep_db?useSSL=false&serverTimezone=UTC";

    public static Connection conectar() {
        try {
            // driver do banco de dados
            Class.forName(CLASSE_DRIVE);
            return DriverManager.getConnection(URL_SERVIDOR, USUARIO, SENHA);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            System.exit(-7);
            return null;
        }
    }

//    public static void desconectar(Connection conn) {
//        if (conn != null) {
//            try {
//                conn.close();
//                System.out.println("Conexão fechada com sucesso.");
//            } catch (SQLException e) {
//                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
//            }
//        }
//    }


}
