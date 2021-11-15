/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.dto.response.general.ubicacion;

public class DistritoResponse {

    //Atributos
    private Long idProvincia;
    private Long idDistrito;
    private String nombreDistrito;


    //Constructores
    public DistritoResponse() {
    }

    public DistritoResponse(Long idProvincia, Long idDistrito, String nombreDistrito) {
        this.idProvincia = idProvincia;
        this.idDistrito = idDistrito;
        this.nombreDistrito = nombreDistrito;
    }

    //Getters y Setters
    public Long getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Long idProvincia) {
        this.idProvincia = idProvincia;
    }

    public Long getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(Long idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }
}
