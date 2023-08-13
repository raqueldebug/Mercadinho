package br.com.mercadinho;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ExcecutorBD extends CriadoresBD {

    public static void main(String[] args) {
        try {

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
    } catch (SQLException e)

    {
        e.printStackTrace();
    }}}