package br.com.mercadinho.control;

import br.com.mercadinho.db.ConectorBd;
import br.com.mercadinho.exception.FornecedorException;
import br.com.mercadinho.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoControl {

    public static void main(String[] args) {}

    public void cadastrarProduto(final Produto produto) throws FornecedorException {


        try {
            Connection con = ConectorBd.getConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO PRODUTO (NOME, QUANTIDADE, ID_FORNECEDOR, VALOR, DATA_VALIDADE, QTA_MIN_ESTOQUE, DATA_INCLUSAO) VALUES (? , ?, ?, ?, ?, ? )");
            ps.setString(1, produto.getNomeP());
            ps.setString(2, produto.getQuantd());
            ps.setString(3, produto.getValor());
            ps.setString(4, produto.getDtaval());
            ps.setString(5, produto.getQtdminest());
            ps.setString(6, produto.getDtainclusao());
            ps.execute();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new FornecedorException("Erro ao cadastrar produto, tente mais tarde");
        }
    }
}
