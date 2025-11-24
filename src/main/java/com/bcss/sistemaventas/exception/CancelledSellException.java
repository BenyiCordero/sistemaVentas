package com.bcss.sistemaventas.exception;

public class CancelledSellException extends RuntimeException {
    public CancelledSellException(String message) {
        super(message);
    }
}
