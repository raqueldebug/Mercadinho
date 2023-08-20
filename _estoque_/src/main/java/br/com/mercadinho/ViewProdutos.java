package br.com.mercadinho;

import net.java.dev.designgridlayout.DesignGridLayout;

import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ViewProdutos extends JFrame {


    private JTextArea textArea;
    private JPanel panel;

    public ViewProdutos() {
        setTitle("Produtos Cadastrados");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        textArea = new JTextArea();
        textArea.setEditable(false);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        JScrollPane scrollPane = new JScrollPane(panel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        lista();

        setVisible(true);
    }

    private void lista() {
        // List<String> nomesFornecedores = new ArrayList<>();
        List<String> nnome = new ArrayList<>();
        List<String> quantd = new ArrayList<>();
        List<String> valor = new ArrayList<>();
        List<String> dtaval = new ArrayList<>();
        List<String> qtdminest = new ArrayList<>();
        List<String> dtainclusao = new ArrayList<>();


        try {
            Connection connection = ConectorBd.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT NOME, QUANTIDADE, VALOR, DATA_VALIDADE, QTA_MIN_ESTOQUE, DATA_INCLUSAO FROM PRODUTO");

            while (resultSet.next()) {
                String nome = resultSet.getString("NOME");
                String quantidade = resultSet.getString("QUANTIDADE");
                String vvalor = resultSet.getString("VALOR");
                String ddtaval = resultSet.getString("DATA_VALIDADE");
                String qqtdminest = resultSet.getString("QTA_MIN_ESTOQUE");
                String ddtainclusao = resultSet.getString("DATA_INCLUSAO");

                nnome.add(nome);
                quantd.add(quantidade);
                valor.add(vvalor);
                dtaval.add(ddtaval);
                qtdminest.add(qqtdminest);
                dtainclusao.add(ddtainclusao);

            }

            resultSet.close();
            statement.close();
            connection.close();

            // Exibindo os dados
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < nnome.size(); i++) {
                JTextArea entryText = new JTextArea();
                //ainda estou pensando como vou puxar nome dos fornecedores relacionados

                entryText.append("Nome: " + nnome.get(i) + "\n");
                entryText.append("Quantidade: "+quantd.get(i) + "\n");
                entryText.append("Valor: "+valor.get(i) + "\n");
                entryText.append("Data de Validade: "+dtaval.get(i) + "\n");
                entryText.append("Quantidade mínima de estoque: "+qtdminest.get(i) + "\n");
                entryText.append("Data de Inclusão: "+dtainclusao.get(i) + "\n");


                output.append("-------------\n");
                entryText.setEditable(false);

                panel.add(entryText);
            }
            textArea.setText(output.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewProdutos viewProdutos = new ViewProdutos();
            viewProdutos.setVisible(true);
        });
    }
}


