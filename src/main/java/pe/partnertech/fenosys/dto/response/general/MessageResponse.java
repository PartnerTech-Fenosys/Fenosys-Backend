/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.dto.response.general;

public class MessageResponse {

    //Atributos
    private String message;

    //Constructores
    public MessageResponse() {
    }

    public MessageResponse(String message) {
        this.message = message;
    }

    //Getters y Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
