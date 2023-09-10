package br.com.mercadinho.view;

import br.com.mercadinho.dao.ProdutoDao;
import br.com.mercadinho.db.ConectorBd;
import br.com.mercadinho.exception.FornecedorException;
import br.com.mercadinho.model.Produto;
import net.java.dev.designgridlayout.DesignGridLayout;
import org.jdatepicker.JDatePicker;
import org.jdesktop.swingx.JXDatePicker;
import org.jdatepicker.impl.DateComponentFormatter;

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

    private static JTextField txtnomeP = new JTextField();
    private static JTextField txtquantd = new JTextField();
    private static JTextField txtvalor = new JTextField();
   // private static JTextField txtdtaval = new JTextField();

    static JXDatePicker txtdtaval = new JXDatePicker();
    private static JTextField txtqtdminest = new JTextField();
    private static JTextField txtdtainclusao = new JTextField();

    public static JComboBox<String> fornecedorComboBox;
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
        layout.row().grid(new JLabel("Nome:")).add(txtnomeP);
        layout.row().grid(new JLabel("Quantidade:")).add(txtquantd);
        layout.row().grid(new JLabel("Valor:")).add(txtvalor);
        layout.row().grid(new JLabel("Data de validade:")).add((JComponent) txtdtaval.getComponent(0));
        layout.row().grid(new JLabel("Quantidade mínima de estoque:")).add(txtqtdminest);
        layout.row().grid(new JLabel("Data de inclusão:")).add(txtdtainclusao);

        JPanel buttonPanel = new JPanel();
        JButton cadastrarButton = new JButton("Cadastrar");
        JButton cancelarButton = new JButton("Cancelar");
        JButton ButtonPesquisarProd = new JButton("Pesquisar Produtos");
        JButton retornaMenu = new JButton("Retornar ao menu");


        buttonPanel.add(cadastrarButton);
        buttonPanel.add(cancelarButton);
        buttonPanel.add(ButtonPesquisarProd);

        buttonPanel.add(retornaMenu);



        layout.row().center().add(buttonPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        txtdtaval.setEditable(true);


        ButtonPesquisarProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //condicçao para aparecer apenas os produtos dos fornecedores selecionados
                String itemSelecionado = (String) fornecedorComboBox.getSelectedItem();
                if ("AMBEV".equals(itemSelecionado)) {
                    //criar um view apenas para este selecionado
                    ViewProdutos p = new ViewProdutos(true,false);
                    p.setVisible(true);




                    System.out.println("Item selecionado é ambev");
                }else if("COCA-COLA".equals(itemSelecionado)){
                    //criar um view apenas para este selecionado
                    ViewProdutos p = new ViewProdutos(false,true);
                    p.setVisible(true);


                } else {
                    // Caso contrário, faça outra coisa
                    // Por exemplo, exibir uma mensagem padrão ou realizar outra ação
                    System.out.println("Item selecionado não é coca-cola");
                }







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

                ProdutoDao prodtud = new ProdutoDao();



                Produto produto = new Produto();
                produto.setNomeP(txtnomeP.getText());
                produto.setQuantd(txtquantd.getText());
                produto.setValor(txtvalor.getText());
                produto.setDtaval(String.valueOf(txtdtaval.getDate()));
                produto.setQtdminest(txtqtdminest.getText());
                produto.setDtainclusao(txtdtainclusao.getText());

                try {
                    ProdutoDao.cadastrarProduto(produto);
                } catch (FornecedorException ex) {
                    throw new RuntimeException(ex);
                }


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


            });
        }

        public void setVisible(boolean b) {
        }
    }


    private static void limparDados() {

        txtnomeP.setText("");
        txtquantd.setText("");
        txtvalor.setText("");
        txtdtaval.setEditable(false);
        txtqtdminest.setText("");
        txtdtainclusao.setText("");



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