/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.dto.response.general.ubicacion;

public class ProvinciaResponse {

    //Atributos
    private Long idDepartamento;
    private Long idProvincia;
    private String nombreProvincia;

    //Constructores
    public ProvinciaResponse() {
    }

    public ProvinciaResponse(Long idDepartamento, Long idProvincia, String nombreProvincia) {
        this.idDepartamento = idDepartamento;
        this.idProvincia = idProvincia;
        this.nombreProvincia = nombreProvincia;
    }

    //Getters y Setters
    public Long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Long getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Long idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }
}
