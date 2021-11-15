/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.dto.response.general.ubicacion;

public class DepartamentoResponse {

    //Atributos
    private Long idPais;
    private Long idDepartamento;
    private String nombreDepartamento;

    //Constructores
    public DepartamentoResponse() {
    }

    public DepartamentoResponse(Long idPais, Long idDepartamento, String nombreDepartamento) {
        this.idPais = idPais;
        this.idDepartamento = idDepartamento;
        this.nombreDepartamento = nombreDepartamento;
    }

    //Getters y Setters
    public Long getIdPais() {
        return idPais;
    }

    public void setIdPais(Long idPais) {
        this.idPais = idPais;
    }

    public Long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }
}
