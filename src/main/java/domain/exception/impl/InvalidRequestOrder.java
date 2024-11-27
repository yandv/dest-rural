package domain.exception.impl;

public class InvalidRequestOrder extends BadRequestException {

    public InvalidRequestOrder() {
        super("O número de pedido de estágio inválido ou inexistente");
    }

}
