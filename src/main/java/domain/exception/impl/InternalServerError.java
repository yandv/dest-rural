package domain.exception.impl;

import domain.exception.DomainException;

public class InternalServerError extends DomainException {

    public InternalServerError(String message) {
        super(message, 500);
    }

    public InternalServerError() {
        this("Ocorreu um erro interno no servidor, tente novamente mais tarde.");
    }

}
