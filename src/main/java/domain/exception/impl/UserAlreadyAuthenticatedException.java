package domain.exception.impl;

import domain.exception.DomainException;

public class UserAlreadyAuthenticatedException extends DomainException {

    public UserAlreadyAuthenticatedException() {
        super("Usuário já autenticado.", 409);
    }
}
