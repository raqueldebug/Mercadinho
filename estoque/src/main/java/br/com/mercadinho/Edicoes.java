package br.com.mercadinho;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
public class Edicoes extends CriadoresBD {

    public static void main(String[] args) {
        try {
            // Comando SQL para criar uma tabela
            String createTableSQL = "CREATE TABLE produtos (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "nome VARCHAR(255) NOT NULL," +
                    "quantidade INT NOT NULL," +
                    "id_fornecedor INT NOT NULL," +
                    "valor DECIMAL(10, 2) NOT NULL," +
                    "data_validade DATE NOT NULL," +
                    "qta_min_estoque INT NOT NULL," +
                    "data_inclusao DATE NOT NULL)";

            // Executa o comando SQL para criar a tabela
            statement.executeUpdate(createTableSQL);

            System.out.println("Tabela criada com sucesso!");

            // Comando SQL para inserir registros na tabela
            String insertSQL1 = "INSERT INTO PRODUTO (id_produto, nome, quantidade, id_fornecedor, valor, data_validade, qta_min_estoque, data_inclusao) " +
                    "VALUES (3, 'Ades', 10, 2, 3.00, '2026-10-01', 2, '2023-08-06')";

            String insertSQL2 = "INSERT INTO PRODUTO (id_produto, nome, quantidade, id_fornecedor, valor, data_validade, qta_min_estoque, data_inclusao) " +
                    "VALUES (4, 'Del-Valle', 10, 2, 6.00, '2026-10-01', 2, '2023-08-06')";

            // Executa os comandos SQL para inserir os registros
            statement.executeUpdate(insertSQL1);
            statement.executeUpdate(insertSQL2);

            System.out.println("Registros inseridos com sucesso!");

            // Fecha os recursos
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
