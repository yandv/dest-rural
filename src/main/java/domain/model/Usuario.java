package domain.model;

import java.util.UUID;

import domain.exception.impl.UserInvalidPasswordException;

public abstract class Usuario {

    private String id;

    private String email;
    private String nome;
    private String senha;

    public Usuario(String email, String nome, String senha) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }

    public void validarSenha(String senha) {
        if (!this.senha.equals(senha)) {
            throw new UserInvalidPasswordException();
        }
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

}
