package br.com.mercadinho.view;

import br.com.mercadinho.view.ViewProdutos;
import net.java.dev.designgridlayout.DesignGridLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());

    }

    public App(){
        createAndShowGUI();
    }

    private static void createAndShowGUI() {

        JFrame frame = new JFrame("Menu de escolhas");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);




        DesignGridLayout layout = new DesignGridLayout(frame.getContentPane());
        layout.row().grid(new JLabel("Escolha o item desejado:"));


        JPanel buttonPanel = new JPanel();
        JButton ButtonFornecedor = new JButton("Cadastrar Fornecedor");
        JButton ButtonProduto = new JButton("Cadastrar Produto");


        buttonPanel.add(ButtonFornecedor);
        buttonPanel.add(ButtonProduto);




        layout.row().center().add(buttonPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ButtonFornecedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TelaFornecedor telaF = new TelaFornecedor();

                telaF.getOwner();

            }
        });

        ButtonProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            TelaProduto telap = new TelaProduto();
            telap.setVisible(true);


                //ARRUMAR
            }
        });







    }


    public static void setVisible(boolean b) {
    }


}

