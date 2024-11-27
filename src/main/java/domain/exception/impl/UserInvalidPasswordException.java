package domain.exception.impl;

import domain.exception.DomainException;

public class UserInvalidPasswordException extends DomainException {

    public UserInvalidPasswordException() {
        super("Usuário ou senha inválido.", 404);
    }
    
}
