package br.com.mercadinho.view;

import br.com.mercadinho.db.ConectorBd;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ViewProdutos extends JFrame {

    private JTextArea textArea;
    private JPanel panel;

    public ViewProdutos(boolean isAmbevSelected, boolean isCocaSelected) {
        setTitle("Produtos Cadastrados");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        textArea = new JTextArea();
        textArea.setEditable(false);

        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1)); // Usar GridLayout flexível

        JScrollPane scrollPane = new JScrollPane(panel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        loadProducts(isAmbevSelected, isCocaSelected); // Carregar os produtos correspondentes
        setVisible(true);
    }

    private void loadProducts(boolean isAmbevSelected, boolean isCocaSelected) {
        List<String> columnNames = List.of("Nome", "Quantidade", "Valor", "Data de Validade", "Quantidade Mínima", "Data de Inclusão");
        List<String[]> data = new ArrayList<>();

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

        displayData(columnNames, data);
    }

    private void displayData(List<String> columnNames, List<String[]> data) {
        JTable table = new JTable(data.toArray(new String[0][]), columnNames.toArray());
        table.setEnabled(false); // Desativar edição dos dados

        panel.add(new JScrollPane(table));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViewProdutos(false, false);
        });
    }
}
