package br.com.mercadinho.model;

import java.util.ArrayList;
import java.util.List;

public class Produto {
    private int id;
    private String nomeP;
    private String quantd;
    private String valor;
    private String dtaval;
    private String qtdminest;
    private String dtainclusao;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNomeP() {
        return nomeP;
    }

    public void setNomeP(String nomeP) {
        this.nomeP = nomeP;
    }

    public String getQuantd() {
        return quantd;
    }

    public void setQuantd(String quantd) {
        this.quantd = quantd;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDtaval() {
        return dtaval;
    }

    public void setDtaval(String dtaval) {
        this.dtaval = dtaval;
    }


    public String getQtdminest() {
        return qtdminest;
    }

    public void setQtdminest(String qtdminest) {
        this.qtdminest = qtdminest;
    }

    public String getDtainclusao() {
        return dtainclusao;
    }

    public void setDtainclusao(String dtainclusao) {
        this.dtainclusao = dtainclusao;
    }

}