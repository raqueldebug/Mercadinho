package br.com.mercadinho;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelaFornecedor extends JFrame {

    private JTextField nomeText;
    private JTextField celularText;
    private JTextField cnpjText;
    private JTextField emailText;


    public TelaFornecedor() {
        setTitle("Cadastrar Fornecedor");
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(90, 90, 90, 90);


        JLabel nomeLabel = new JLabel("Nome:");
        nomeText = new JTextField(20);

        JLabel celularLabel = new JLabel("Celular:");
        celularText = new JTextField(20);

        JLabel cnpjLabel = new JLabel("CNPJ:");
        cnpjText = new JTextField(20);

        JLabel emailLabel = new JLabel("E-mail:");
        emailText = new JTextField(20);

        JButton cadastrarButton = new JButton("Cadastrar");
        JButton atualizarButton = new JButton("Pesquisar");
        JButton limpardados = new JButton("Limpar dados");

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

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisar();
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpardados();
            }
        });

        panel.add(nomeLabel);
        panel.add(nomeText);

        panel.add(celularLabel);
        panel.add(celularText);

        panel.add(cnpjLabel);
        panel.add(cnpjText);

        panel.add(emailLabel);
        panel.add(emailText);

        panel.add(cadastrarButton);
        panel.add(atualizarButton);

        getContentPane().add(panel);
        setVisible(true);
    }

    private void cadastrar() throws SQLException {
        String nnomeText = nomeText.getText();
        String ccelularText = celularText.getText();
        String ccnpjText = cnpjText.getText();
        String eemailText = emailText.getText();

        try {
            String sql = "INSERT INTO FORNECEDOR (NOME, CNPJ, CELULAR, EMAIL) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = (PreparedStatement) DatabasesConnection.getConnection(sql);
            preparedStatement.setString(1, nnomeText);
            preparedStatement.setString(2, ccelularText);
            preparedStatement.setString(3, ccnpjText);
            preparedStatement.setString(4, eemailText);

            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Dados inseridos no banco de dados: ");
            preparedStatement.close();
            DatabasesConnection.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }


        // Lógica para cadastrar os dados no banco de dados ou fazer outra ação necessária.


    }


    private void pesquisar() {
        String nome = nomeText.getText();
        String celular = celularText.getText();

        // Lógica para atualizar os dados no banco de dados ou fazer outra ação necessária.
        // Exemplo: exibir uma mensagem de sucesso.
        JOptionPane.showMessageDialog(this, "Dados atualizados: " + nome + ", " + celular);
    }



    private void limpardados() {
        String nome = nomeText.getText();
        String celular = celularText.getText();

        // Lógica para atualizar os dados no banco de dados ou fazer outra ação necessária.
        // Exemplo: exibir uma mensagem de sucesso.
        JOptionPane.showMessageDialog(this, "Dados atualizados: " + nome + ", " + celular);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaFornecedor());
    }
}






