/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.dto.request.usuario.signup;

public class SignupAdminRequest {

    //Atributos
    private String utilitytokenUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private Long distritoUsuario;
    private String passwordUsuario;

    //Constructores
    public SignupAdminRequest() {
    }

    public SignupAdminRequest(String utilitytokenUsuario, String nombreUsuario, String apellidoUsuario,
                              Long distritoUsuario, String passwordUsuario) {
        this.utilitytokenUsuario = utilitytokenUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.distritoUsuario = distritoUsuario;
        this.passwordUsuario = passwordUsuario;
    }

    //Getters y Setters
    public String getUtilitytokenUsuario() {
        return utilitytokenUsuario;
    }

    public void setUtilitytokenUsuario(String utilitytokenUsuario) {
        this.utilitytokenUsuario = utilitytokenUsuario;
    }

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

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }
}
