package br.com.mercadinho.dao;

import br.com.mercadinho.exception.FornecedorException;
import br.com.mercadinho.model.Fornecedor;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.mercadinho.db.ConectorBd;
import br.com.mercadinho.exception.FornecedorException;
import br.com.mercadinho.model.Fornecedor;

public class FornecedorDao {

    public void cadastrar(final Fornecedor fornecedor) throws FornecedorException {
        try {
            Connection con = ConectorBd.getConnection();
            PreparedStatement ps = con
                    .prepareStatement("INSERT INTO FORNECEDOR (NOME, CNPJ, CELULAR, EMAIL) VALUES (? , ?, ?, ? )");
            ps.setString(1, fornecedor.getNome());
            ps.setString(2, fornecedor.getCnpj());
            ps.setString(3, fornecedor.getCelular());
            ps.setString(4, fornecedor.getEmail());
            ps.execute();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new FornecedorException("Erro ao cadastrar fornecedor, tente mais tarde");
        }
    }


}

