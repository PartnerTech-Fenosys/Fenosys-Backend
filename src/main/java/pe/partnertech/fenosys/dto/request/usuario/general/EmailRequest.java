/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.dto.request.usuario.general;

public class EmailRequest {

    //Atributos
    private String emailUsuario;

    //Constructores
    public EmailRequest() {
    }

    public EmailRequest(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    //Getters y Setters
    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }
}
