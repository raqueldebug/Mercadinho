package br.com.mercadinho;

import net.java.dev.designgridlayout.DesignGridLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TelaProduto extends JFrame {

    private static JComboBox<String> fornecedorComboBox;
    private static Map<JLabel, JTextField> camposTexto = new HashMap<>(); // Mapa para armazenar rótulos e campos de texto

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> createAndShowGUI());


    }

public TelaProduto(){
    createAndShowGUI();
}

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Cadastro Produto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        DesignGridLayout layout = new DesignGridLayout(frame.getContentPane());

        fornecedorComboBox = new JComboBox<>();
        preencherComboBox();  // Chama o método para preencher o JComboBox com os dados do banco de dados
        layout.row().grid(new JLabel("Fornecedor:")).add(fornecedorComboBox);
        layout.row().grid(new JLabel("Nome:")).add(new JTextField(20));
        layout.row().grid(new JLabel("Quantidade:")).add(new JTextField(20));
        layout.row().grid(new JLabel("Valor:")).add(new JTextField(20));
        layout.row().grid(new JLabel("Data de validade:")).add(new JTextField(20));
        layout.row().grid(new JLabel("Quantidade mínima de estoque:")).add(new JTextField(20));
        layout.row().grid(new JLabel("Data de inclusão:")).add(new JTextField(20));

        JPanel buttonPanel = new JPanel();
        JButton cadastrarButton = new JButton("Cadastrar");
        JButton cancelarButton = new JButton("Cancelar");
        JButton limparButton = new JButton("Limpar dados");
        JButton retornaMenu = new JButton("Retornar ao menu");

        buttonPanel.add(cadastrarButton);
        buttonPanel.add(cancelarButton);
        buttonPanel.add(limparButton);
        buttonPanel.add(retornaMenu);


        layout.row().center().add(buttonPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


      /*  TelaMenu menu = new TelaMenu();
        menu.setVisible(true);


        retornaMenu.addActionListener(new ActionListener() {
            private boolean telaVisivel = true;
            @Override
            public void actionPerformed(ActionEvent e) {
                   if (telaVisivel) {
                       menu.setVisible(false); // Fecha a tela
                        telaVisivel = false;
                    } else {
                       menu.setVisible(true);  // Reabre a tela
                        telaVisivel = true;
                    }
                }


        });
        frame.getContentPane().add(retornaMenu);*/



        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(cadastrarButton, "CADASTRADO!!!");

            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparDados();
            }
        });



        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fechar a janela ao clicar no botão Cancelar
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(cancelarButton);
                frame.dispose(); // Fecha a janela
            }
        });

    }

    public void retornamenu(){


    }
    private static void limparDados() {

    }



    private static void preencherComboBox() {
        List<String> nomesFornecedores = new ArrayList<>();

        try {
            Connection conexao = ConectorBd.getConnection();
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT NOME FROM FORNECEDOR");


            while (resultSet.next()) {
                String nomeFornecedor = resultSet.getString("nome");
                nomesFornecedores.add(nomeFornecedor);
            }

            resultSet.close();
            statement.close();
            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String nomeFornecedor : nomesFornecedores) {
            fornecedorComboBox.addItem(nomeFornecedor);
        }
    }




    public void setVisible(boolean b) {


        b= true;
    }
}