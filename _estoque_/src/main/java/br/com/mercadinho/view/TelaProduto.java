package br.com.mercadinho.view;

import br.com.mercadinho.model.Produto;
import br.com.mercadinho.ViewProdutos;
import br.com.mercadinho.db.ConectorBd;
import net.java.dev.designgridlayout.DesignGridLayout;

import javax.swing.*;
import java.awt.*;
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

    Produto produto = new Produto();

    private static JTextField nomeP = new JTextField(20);
    private static JTextField quantd = new JTextField(20);
    private static JTextField valor = new JTextField(20);
    private static JTextField dtaval = new JTextField(20);
    private static JTextField qtdminest = new JTextField(20);
    private static JTextField dtainclusao = new JTextField(20);

    private static JComboBox<String> fornecedorComboBox;
    private static Map<JLabel, JTextField> camposTexto = new HashMap<>(); // Mapa para armazenar rótulos e campos de texto

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> createAndShowGUI());


    }

    public TelaProduto() {
        createAndShowGUI();
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Cadastro Produto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        DesignGridLayout layout = new DesignGridLayout(frame.getContentPane());

        fornecedorComboBox = new JComboBox<>();

        preencherComboBox();  // Chama o método para preencher o JComboBox com os dados do banco de dados
        layout.row().grid(new JLabel("Fornecedor:")).add(fornecedorComboBox);
        layout.row().grid(new JLabel("Nome:")).add(nomeP);
        //layout.row().grid(new JLabel("Nome:")).add(new JTextField(20));
        layout.row().grid(new JLabel("Quantidade:")).add(quantd);
        layout.row().grid(new JLabel("Valor:")).add(valor);
        layout.row().grid(new JLabel("Data de validade:")).add(dtaval);
        layout.row().grid(new JLabel("Quantidade mínima de estoque:")).add(qtdminest);
        layout.row().grid(new JLabel("Data de inclusão:")).add(dtainclusao);

        JPanel buttonPanel = new JPanel();
        JButton cadastrarButton = new JButton("Cadastrar");
        JButton cancelarButton = new JButton("Cancelar");

        JButton retornaMenu = new JButton("Retornar ao menu");
        JButton listaproduto = new JButton("Pesquisar");

        buttonPanel.add(cadastrarButton);
        buttonPanel.add(cancelarButton);

        buttonPanel.add(retornaMenu);
        buttonPanel.add(listaproduto);


        layout.row().center().add(buttonPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        listaproduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ViewProdutos viewP = new ViewProdutos();
                viewP.setVisible(true);
                //POSSO MELHORAR A PESQUISA PARA O TIPO QUE FOI SELECIONADO NO MENUZINHO DE FORNECEDOR

            }
        });




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


        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparDados();
            }
        });

    }

    public void retornamenu() {


    }

    public void testcadastro(){


    }
    public class listaprodutos {

        private JTextArea textArea;
        private JPanel panel;

        public listaprodutos() {
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
                    entryText.append("Quantidade: " + quantd.get(i) + "\n");
                    entryText.append("Valor: " + valor.get(i) + "\n");
                    entryText.append("Data de Validade: " + dtaval.get(i) + "\n");
                    entryText.append("Quantidade mínima de estoque: " + qtdminest.get(i) + "\n");
                    entryText.append("Data de Inclusão: " + dtainclusao.get(i) + "\n");


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

        public void setVisible(boolean b) {
        }
    }


    private static void limparDados() {

        nomeP.setText("");
        quantd.setText("");
        valor.setText("");
        dtaval.setText("");
        qtdminest.setText("");
        dtainclusao.setText("");



    }


    private static void preencherComboBox() {
        List<String> nomesFornecedores = new ArrayList<>();
        String branco = "";

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
        fornecedorComboBox.addItem(branco);
        for (String nomeFornecedor : nomesFornecedores) {

            fornecedorComboBox.addItem(nomeFornecedor);
        }
    }


}