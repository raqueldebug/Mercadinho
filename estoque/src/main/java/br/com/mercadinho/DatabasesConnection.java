package br.com.mercadinho;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabasesConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mercadinho_db";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "senha";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}
