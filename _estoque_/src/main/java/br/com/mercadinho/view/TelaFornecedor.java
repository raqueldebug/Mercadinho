package br.com.mercadinho.view;

import br.com.mercadinho.dao.FornecedorDao;
import br.com.mercadinho.exception.FornecedorException;
import br.com.mercadinho.model.Fornecedor;
import net.java.dev.designgridlayout.DesignGridLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaFornecedor extends JFrame {



    Fornecedor fornecedor = new Fornecedor();



    public JTextField nomeText = new JTextField();
    private JTextField celularText = new JTextField();
    private JTextField cnpjText = new JTextField();
    private JTextField emailText = new JTextField();

    public void preencherCamposComNome(Fornecedor fornecedor) {
        nomeText.setText(fornecedor.getNome());
        celularText.setText(fornecedor.getCelular());
        cnpjText.setText(fornecedor.getCnpj());
        emailText.setText(fornecedor.getEmail());
    }

    public TelaFornecedor() {



        JFrame frame = new JFrame("Cadastro Fornecedor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DesignGridLayout layout = new DesignGridLayout(frame.getContentPane());
        JButton PequisarButton = new JButton("Pesquisar");


        layout.row().grid(new JLabel("CNPJ:")).add(cnpjText).add(PequisarButton);
        layout.row().grid(new JLabel("Nome:")).add(nomeText);
        layout.row().grid(new JLabel("Celular:")).add(celularText);
        layout.row().grid(new JLabel("E-mail:")).add(emailText);




        JPanel buttonPanel = new JPanel();
        JButton cadastrarButton = new JButton("Cadastrar");

        JButton cancelar = new JButton("Cancelar");
        JButton returnaMenu = new JButton("Retornar ao menu");

        buttonPanel.add(cadastrarButton);

        buttonPanel.add(cancelar);
        buttonPanel.add(returnaMenu);

        layout.row().center().add(buttonPanel);


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        FornecedorDao fc = new FornecedorDao();


        Fornecedor fornecedor = new Fornecedor();




        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setNome(nomeText.getText());
                fornecedor.setCnpj(cnpjText.getText());
                fornecedor.setCelular(celularText.getText());
                fornecedor.setEmail(emailText.getText());


                    try {
                        fc.cadastrar(fornecedor); // Chama o método cadastrar com o fornecedor preenchido
                        JOptionPane.showMessageDialog(frame, "Fornecedor cadastrado com sucesso.");
                    } catch (FornecedorException ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage());
                    }
                }

        });
        returnaMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App menu = new App();
                menu.setVisible(true);
            }
        });

        PequisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String cnpj = cnpjText.getText();
                fc.pesquisar(String.valueOf(cnpjText));



                if (!cnpj.isEmpty()) {
                    Fornecedor fornecedorEncontrado = fc.pesquisar(cnpj);
                    if (fornecedorEncontrado != null) {

                        nomeText.setText(fornecedorEncontrado.getNome());
                        celularText.setText(fornecedorEncontrado.getCelular());
                        emailText.setText(fornecedorEncontrado.getEmail());

                    } else {
                        JOptionPane.showMessageDialog(frame, "Fornecedor não encontrado.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Por favor, insira um CNPJ para pesquisa.");
                }
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





