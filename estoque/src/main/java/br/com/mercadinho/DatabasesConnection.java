package br.com.mercadinho;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabasesConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mercadinho_db";
    private static final String USERNAME = "raquel";
    private static final String PASSWORD = "Van#2020";

    public static Connection getConnection(String sql) throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }



    public static void close() {
    }
}
