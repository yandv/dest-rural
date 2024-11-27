package domain.model;

public class Discente extends Usuario {

    public Discente(String email, String nome, String senha) {
        super(email, nome, senha);
    }

    public void validarSenha(String senhaFornecida) {
        if (!this.senha.equals(senhaFornecida)) {
            throw new IllegalArgumentException("Senha inválida para o usuário fornecido.");
        }
    }

}
