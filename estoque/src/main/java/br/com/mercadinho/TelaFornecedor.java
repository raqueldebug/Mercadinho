package br.com.mercadinho;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaFornecedor extends JFrame {

    private JTextField nomeText;
    private JTextField celularText;
    private JTextField cnpjText;
    private JTextField emailText;

    public TelaFornecedor() {
        setTitle("Tela Fornecedor");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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
        JButton atualizarButton = new JButton("Atualizar");

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrar();
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizar();
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

    private void cadastrar() {
        String nome = nomeText.getText();
        String celular = celularText.getText();

        // Lógica para cadastrar os dados no banco de dados ou fazer outra ação necessária.
        // Exemplo: exibir uma mensagem de sucesso.
        JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso: " + nome + ", " + celular);
    }

    private void atualizar() {
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

