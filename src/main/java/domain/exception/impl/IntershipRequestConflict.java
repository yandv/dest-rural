package domain.exception.impl;

import domain.exception.DomainException;

public class IntershipRequestConflict extends DomainException {

    public IntershipRequestConflict() {
        super("O pedido de estágio já possui um supervisor", 409);
    }
    
}
