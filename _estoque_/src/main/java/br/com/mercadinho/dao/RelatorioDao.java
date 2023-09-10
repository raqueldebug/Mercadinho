package br.com.mercadinho.dao;

import br.com.mercadinho.db.ConectorBd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RelatorioDao {

    private void loadProducts(boolean isAmbevSelected, boolean isCocaSelected) {
        java.util.List<String> columnNames = java.util.List.of("Nome", "Quantidade", "Valor", "Data de Validade", "Quantidade Mínima", "Data de Inclusão");
        java.util.List<String[]> data = new ArrayList<>();

        int supplierId = isAmbevSelected ? 1 : (isCocaSelected ? 2 : -1);

        if (supplierId != -1) {
            try {
                Connection connection = ConectorBd.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT NOME, QUANTIDADE, VALOR, DATA_VALIDADE, QTA_MIN_ESTOQUE, DATA_INCLUSAO FROM PRODUTO WHERE ID_FORNECEDOR = " + supplierId);

                while (resultSet.next()) {
                    String nome = resultSet.getString("NOME");
                    String quantidade = resultSet.getString("QUANTIDADE");
                    String valor = resultSet.getString("VALOR");
                    String dtaval = resultSet.getString("DATA_VALIDADE");
                    String qtdminest = resultSet.getString("QTA_MIN_ESTOQUE");
                    String dtainclusao = resultSet.getString("DATA_INCLUSAO");

                    data.add(new String[]{nome, quantidade, valor, dtaval, qtdminest, dtainclusao});
                }

                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
