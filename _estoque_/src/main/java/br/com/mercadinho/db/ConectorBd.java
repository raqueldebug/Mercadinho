package br.com.mercadinho.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConectorBd {

    private static Properties properties = new Properties();

    static {
        try {
            InputStream inputStream = ConectorBd.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.user"),
                properties.getProperty("db.password"));
    }
    public static void close() {
    }

    public static Object getConnection(String sql) {
        return sql;
    }
}