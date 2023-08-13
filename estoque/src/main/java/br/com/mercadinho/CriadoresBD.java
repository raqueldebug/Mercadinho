package br.com.mercadinho;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
public class CriadoresBD {
    static Connection connection;

    static {
        try {
            // Obtém uma conexão com o banco de dados
            connection = DatabasesConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    static Statement statement;

    static {
        try {
            // Cria um objeto Statement para executar comandos SQL
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
