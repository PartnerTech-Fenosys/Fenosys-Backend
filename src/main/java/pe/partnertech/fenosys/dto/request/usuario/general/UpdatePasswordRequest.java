/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.dto.request.usuario.general;

public class UpdatePasswordRequest {

    //Atributos
    private String utilityToken;
    private String passwordUsuario;

    //Constructores

    public UpdatePasswordRequest() {
    }

    public UpdatePasswordRequest(String utilityToken, String passwordUsuario) {
        this.utilityToken = utilityToken;
        this.passwordUsuario = passwordUsuario;
    }

    //Getters y Setters
    public String getUtilityToken() {
        return utilityToken;
    }

    public void setUtilityToken(String utilityToken) {
        this.utilityToken = utilityToken;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }
}
