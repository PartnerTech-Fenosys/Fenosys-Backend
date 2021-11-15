/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.dto.request.usuario;

public class UpdateBasicInfoRequest {

    //Atributos
    private String nombreUsuario;
    private String apellidoUsuario;
    private Long distritoUsuario;

    //Constructores
    public UpdateBasicInfoRequest() {
    }

    public UpdateBasicInfoRequest(String nombreUsuario, String apellidoUsuario, Long distritoUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.distritoUsuario = distritoUsuario;
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

    public Long getDistritoUsuario() {
        return distritoUsuario;
    }

    public void setDistritoUsuario(Long distritoUsuario) {
        this.distritoUsuario = distritoUsuario;
    }
}
