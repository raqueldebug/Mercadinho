package br.com.mercadinho.view;

import net.java.dev.designgridlayout.DesignGridLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RelatoriosView {
    public RelatoriosView() {
        createAndShowGUI();
    }

    private static void createAndShowGUI() {
         JTextField digiteOpcao = new JTextField();

        JFrame frame = new JFrame("Emissão de relatórios");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        DesignGridLayout layout = new DesignGridLayout(frame.getContentPane());
        layout.row().grid(new JLabel("Escolha o item desejado:"));


        JPanel buttonPanel = new JPanel();
        JButton ButtonFornecedor = (JButton) new JButton("Cadastrar Fornecedor").add(digiteOpcao);
        JButton ButtonProduto = new JButton("Cadastrar Produto");
//posso fazer a caixa de texto e a partir do digitado ele entra n opção desejada

        layout.row().grid(new JLabel("Nome:"));

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


      /*  ButtonProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TelaProduto telap = new TelaProduto();
                telap.setVisible(true);


                //ARRUMAR
            }
        });*/


    }


    public static void setVisible(boolean b) {
    }

}
