package br.com.mercadinho;
import javax.swing.*;
import net.java.dev.designgridlayout.DesignGridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.EventListener;


import static com.sun.org.apache.xerces.internal.util.DOMUtil.setVisible;


public class Menu  {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());

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
        JButton ButtonPesquisarProd = new JButton("Pesquisar Produtos cadastrados");

        buttonPanel.add(ButtonFornecedor);
        buttonPanel.add(ButtonProduto);
        buttonPanel.add(ButtonPesquisarProd);



        layout.row().center().add(buttonPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ButtonFornecedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TelaFornecedor telaF = new TelaFornecedor();
                telaF.setVisible(true);

            }
        });

        ButtonProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TelaProduto telaP = new TelaProduto();
                telaP.setVisible(true);

                //ARRUMAR
            }
        });
        ButtonPesquisarProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //criar tela


            }
        });






        }



    }


