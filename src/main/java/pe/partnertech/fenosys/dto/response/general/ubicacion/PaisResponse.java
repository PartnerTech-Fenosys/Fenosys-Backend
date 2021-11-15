/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.dto.response.general.ubicacion;

public class PaisResponse {

    //Atributos
    private Long idPais;
    private String nombrePais;

    //Constructores
    public PaisResponse() {
    }

    public PaisResponse(Long idPais, String nombrePais) {
        this.idPais = idPais;
        this.nombrePais = nombrePais;
    }

    //Getters y Setters
    public Long getIdPais() {
        return idPais;
    }

    public void setIdPais(Long idPais) {
        this.idPais = idPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
}
