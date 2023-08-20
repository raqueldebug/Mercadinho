package br.com.mercadinho;
import net.java.dev.designgridlayout.DesignGridLayout;

import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelaFornecedor extends JFrame  {

    private JTextField nomeText;
    private JTextField celularText;
    private JTextField cnpjText;
    private JTextField emailText;


    public TelaFornecedor() {
        JFrame frame = new JFrame("Cadastro Fornecedor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        DesignGridLayout layout = new DesignGridLayout(frame.getContentPane());
        layout.row().grid(new JLabel("Nome:")).add(new JTextField(20));
        layout.row().grid(new JLabel("Celular:")).add(new JTextField(20));
        layout.row().grid(new JLabel("CNPJ:")).add(new JTextField(20));
        layout.row().grid(new JLabel("E-mail:")).add(new JTextField(20));




        JPanel buttonPanel = new JPanel();
        JButton cadastrarButton = new JButton("Cadastrar");
        JButton atualizarButton = new JButton("Pesquisar");
        JButton limpardados = new JButton("Limpar dados");
        JButton returnaMenu = new JButton("Retornar ao menu");

        buttonPanel.add(cadastrarButton);
        buttonPanel.add(atualizarButton);
        buttonPanel.add(limpardados);
        buttonPanel.add(returnaMenu);

        layout.row().center().add(buttonPanel);


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);




        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cadastrar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        returnaMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
         TelaMenu menu = new TelaMenu();
                menu.setVisible(true);
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisar();
            }
        });

        limpardados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpardados();
            }
        });




        setVisible(true);
    }

    private void cadastrar() throws SQLException {
        String nnomeText = nomeText.getText();
        String ccelularText = celularText.getText();
        String ccnpjText = cnpjText.getText();
        String eemailText = emailText.getText();

        try {
            String sql = "INSERT INTO FORNECEDOR (NOME, CNPJ, CELULAR, EMAIL) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = (PreparedStatement) ConectorBd.getConnection(sql);
            preparedStatement.setString(1, nnomeText);
            preparedStatement.setString(2, ccelularText);
            preparedStatement.setString(3, ccnpjText);
            preparedStatement.setString(4, eemailText);

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Dados inseridos no banco de dados: ");
            preparedStatement.close();
            ConectorBd.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }


        // Lógica para cadastrar os dados no banco de dados ou fazer outra ação necessária.


    }


    private void pesquisar() {
        String nome = nomeText.getText();
        String celular = celularText.getText();
        String cnpj = cnpjText.getText();
        String email = emailText.getText();


        //POSSO CHAMAR UMA PAGE COM TODOS OS FORNECEDORES CADASTRADOS




        // Lógica para atualizar os dados no banco de dados ou fazer outra ação necessária.
        // Exemplo: exibir uma mensagem de sucesso.
        JOptionPane.showMessageDialog(this, "Dados atualizados: " );
    }



    private void limpardados() {
        String nome = nomeText.getText();
        String celular = celularText.getText();

        // Lógica para atualizar os dados no banco de dados ou fazer outra ação necessária.
        // Exemplo: exibir uma mensagem de sucesso.


        nomeText.setText("");
        celularText.setText("");
        cnpjText.setText("");
        emailText.setText("");

        JOptionPane.showMessageDialog(this, "dados apagado");
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaFornecedor());
    }
}





