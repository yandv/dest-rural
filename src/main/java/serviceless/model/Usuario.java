package serviceless.model;

import java.util.UUID;

public abstract class Usuario {

    private String id;

    private String email;
    private String nome;
    private String senha;

    private UsuarioType tipo;

    public Usuario(String email, String nome, String senha, UsuarioType tipo) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
    }

    public boolean verificaSenha(String senha) {
        return this.senha.equals(senha);
    }
    
}
