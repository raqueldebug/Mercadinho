package br.com.mercadinho.view;

import br.com.mercadinho.control.FornecedorControl;
import br.com.mercadinho.exception.FornecedorException;
import br.com.mercadinho.model.Fornecedor;
import net.java.dev.designgridlayout.DesignGridLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaFornecedor extends JFrame {



    public JTextField nomeText = new JTextField();

    private JTextField celularText = new JTextField();
    private JTextField cnpjText = new JTextField();
    private JTextField emailText = new JTextField();


    public TelaFornecedor() {


        JFrame frame = new JFrame("Cadastro Fornecedor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DesignGridLayout layout = new DesignGridLayout(frame.getContentPane());
        layout.row().grid(new JLabel("Nome:")).add(nomeText);
        layout.row().grid(new JLabel("Celular:")).add(celularText);
        layout.row().grid(new JLabel("CNPJ:")).add(cnpjText);
        layout.row().grid(new JLabel("E-mail:")).add(emailText);


        JPanel buttonPanel = new JPanel();
        JButton cadastrarButton = new JButton("Cadastrar");
        JButton atualizarButton = new JButton("Pesquisar");
        JButton cancelar = new JButton("Cancelar");
        JButton returnaMenu = new JButton("Retornar ao menu");

        buttonPanel.add(cadastrarButton);
        buttonPanel.add(atualizarButton);
        buttonPanel.add(cancelar);
        buttonPanel.add(returnaMenu);

        layout.row().center().add(buttonPanel);


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print(nomeText);
              /*  try {
                    fc.cadastrar();

                } catch (FornecedorException ex) {
                    throw new RuntimeException(ex);
                }*/

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
                FornecedorControl fc = new FornecedorControl();
                Fornecedor f = new Fornecedor();
               Fornecedor rs = fc.pesquisar(f.getCnpj());

            }
        });

        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpardados();
            }
        });


        //setVisible(true);
    }


    private void pesquisar() {
        String nome = nomeText.getText();
        String celular = celularText.getText();
        String cnpj = cnpjText.getText();
        String email = emailText.getText();


        //POSSO CHAMAR UMA PAGE COM TODOS OS FORNECEDORES CADASTRADOS


        // Lógica para atualizar os dados no banco de dados ou fazer outra ação necessária.
        // Exemplo: exibir uma mensagem de sucesso.
        JOptionPane.showMessageDialog(this, "Dados atualizados: ");
    }


    private void limpardados() {
        nomeText.setText("");
        celularText.setText("");
        cnpjText.setText("");
        emailText.setText("");
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaFornecedor());
    }
}





