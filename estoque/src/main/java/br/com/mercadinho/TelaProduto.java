package br.com.mercadinho;

import net.java.dev.designgridlayout.DesignGridLayout;

import javax.swing.*;


public class  TelaProduto {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());


    }



    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Cadastro Produto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        DesignGridLayout layout = new DesignGridLayout(frame.getContentPane());
        layout.row().grid(new JLabel("Nome:")).add(new JTextField(20));
        layout.row().grid(new JLabel("Quantidade:")).add(new JTextField(20));
        layout.row().grid(new JLabel("Fornecedor:")).add(new JTextField(20));
        layout.row().grid(new JLabel("Valor:")).add(new JTextField(20));
        layout.row().grid(new JLabel("Data de validade:")).add(new JTextField(20));
        layout.row().grid(new JLabel("Quantidade mínima de estoque:")).add(new JTextField(20));
        layout.row().grid(new JLabel("Data de inclusão:")).add(new JTextField(20));

        JPanel buttonPanel = new JPanel();
        JButton cadastrarButton = new JButton("Cadastrar");
        JButton cancelarButton = new JButton("Cancelar");
        buttonPanel.add(cadastrarButton);
        buttonPanel.add(cancelarButton);

        layout.row().center().add(buttonPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }


    public void setVisible(boolean b) {


        b= true;
    }
}

