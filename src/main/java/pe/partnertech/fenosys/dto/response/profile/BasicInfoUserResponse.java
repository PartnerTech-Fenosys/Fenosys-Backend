/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.dto.response.profile;

import pe.partnertech.fenosys.dto.response.general.ImagenResponse;

public class BasicInfoUserResponse {

    //Atributos
    private String nombreUsuario;
    private String apellidoUsuario;
    private String paisUsuario;
    private String departamentoUsuario;
    private String provinciaUsuario;
    private String distritoUsuario;
    private String emailUsuario;
    private ImagenResponse imagenUsuario;

    //Constructores
    public BasicInfoUserResponse() {
    }

    public BasicInfoUserResponse(String nombreUsuario, String apellidoUsuario, String paisUsuario, String departamentoUsuario,
                                 String provinciaUsuario, String distritoUsuario, String emailUsuario,
                                 ImagenResponse imagenUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.paisUsuario = paisUsuario;
        this.departamentoUsuario = departamentoUsuario;
        this.provinciaUsuario = provinciaUsuario;
        this.distritoUsuario = distritoUsuario;
        this.emailUsuario = emailUsuario;
        this.imagenUsuario = imagenUsuario;
    }

    //Getters y Setters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getPaisUsuario() {
        return paisUsuario;
    }

    public void setPaisUsuario(String paisUsuario) {
        this.paisUsuario = paisUsuario;
    }

    public String getDepartamentoUsuario() {
        return departamentoUsuario;
    }

    public void setDepartamentoUsuario(String departamentoUsuario) {
        this.departamentoUsuario = departamentoUsuario;
    }

    public String getProvinciaUsuario() {
        return provinciaUsuario;
    }

    public void setProvinciaUsuario(String provinciaUsuario) {
        this.provinciaUsuario = provinciaUsuario;
    }

    public String getDistritoUsuario() {
        return distritoUsuario;
    }

    public void setDistritoUsuario(String distritoUsuario) {
        this.distritoUsuario = distritoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public ImagenResponse getImagenUsuario() {
        return imagenUsuario;
    }

    public void setImagenUsuario(ImagenResponse imagenUsuario) {
        this.imagenUsuario = imagenUsuario;
    }
}
