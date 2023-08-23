package br.com.mercadinho.exception;

public class FornecedorException extends Exception{

    public FornecedorException(String msg) {
        super(msg);
    }

    public static class SQLException extends Throwable {
    }
}
