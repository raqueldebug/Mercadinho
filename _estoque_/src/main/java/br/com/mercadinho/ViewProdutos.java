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

        public ViewProdutos() {
            setTitle("Produtos Cadastrados");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 300);

            textArea = new JTextArea();
            textArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(textArea);
            getContentPane().add(scrollPane, BorderLayout.CENTER);

            lista();

            setVisible(true);
        }

        private void lista() {
            List<String> nomesFornecedores = new ArrayList<>();
            List<String> quantd = new ArrayList<>();

            try {
                Connection connection = ConectorBd.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT NOME, QUANTIDADE FROM PRODUTO");

                while (resultSet.next()) {
                    String nome = resultSet.getString("NOME");
                    String quantidade = resultSet.getString("QUANTIDADE");
                    nomesFornecedores.add(nome);
                    quantd.add(quantidade);
                }

                resultSet.close();
                statement.close();
                connection.close();

                // Exibindo os dados
                StringBuilder output = new StringBuilder();
                for (int i = 0; i < nomesFornecedores.size(); i++) {
                    output.append("Nome: ").append(nomesFornecedores.get(i)).append("\n");
                    output.append("Quantidade: ").append(quantd.get(i)).append("\n");
                    output.append("-------------\n");
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


