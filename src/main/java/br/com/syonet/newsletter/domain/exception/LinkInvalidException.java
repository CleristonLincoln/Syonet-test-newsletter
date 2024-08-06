package br.com.syonet.newsletter.domain.exception;

import java.io.Serial;

public class LinkInvalidException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public LinkInvalidException() {
        super("Link informado é inválido.");
    }

    public LinkInvalidException(String message) {
        super(message);
    }
}
