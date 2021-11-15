/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.dto.request.usuario;

public class SigninRequest {

    //Atributos
    private String usernameUsuario;
    private String passwordUsuario;

    //Constructores
    public SigninRequest() {
    }

    public SigninRequest(String usernameUsuario, String passwordUsuario) {
        this.usernameUsuario = usernameUsuario;
        this.passwordUsuario = passwordUsuario;
    }

    //Getters y Setters
    public String getUsernameUsuario() {
        return usernameUsuario;
    }

    public void setUsernameUsuario(String usernameUsuario) {
        this.usernameUsuario = usernameUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }
}
