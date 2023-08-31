package br.com.mercadinho.dao;

import br.com.mercadinho.db.ConectorBd;
import br.com.mercadinho.exception.FornecedorException;
import br.com.mercadinho.model.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FornecedorDao {
     Fornecedor f = new Fornecedor();





    public Fornecedor pesquisar(final String cnpj) {


        try {
            Connection con = ConectorBd.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from FORNECEDOR f where f.CNPJ = ?");
            ps.setString(1, cnpj);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { // cada linha cria um fornecedor
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId((int) rs.getLong(1));
                fornecedor.setNome(rs.getString(2));
                fornecedor.setCnpj(rs.getString(3));
                fornecedor.setCelular(rs.getString(4));
                fornecedor.setEmail(rs.getString(5));
                return fornecedor;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }





        return null;
    }


    public void cadastrar(Fornecedor fornecedor) throws FornecedorException {


        try {
            Connection con = ConectorBd.getConnection();
            PreparedStatement ps = con
                    .prepareStatement("INSERT INTO FORNECEDOR (NOME, CNPJ, CELULAR, EMAIL) VALUES (?, ?, ?, ? )");


            ps.setString(1,fornecedor.getNome());
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

