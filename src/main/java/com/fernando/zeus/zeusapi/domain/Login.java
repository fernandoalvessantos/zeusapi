package com.fernando.zeus.zeusapi.domain;

import javax.validation.constraints.NotEmpty;

public class Login {


    @NotEmpty(message = "O email é obrigatório")
    private String email;

    @NotEmpty(message = "A senha é obrigatória")
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
