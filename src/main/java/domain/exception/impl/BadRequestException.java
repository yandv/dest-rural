package domain.exception.impl;

import domain.exception.DomainException;

public class BadRequestException extends DomainException {

    public BadRequestException(String message) {
        super(message, 400);
    }

}
