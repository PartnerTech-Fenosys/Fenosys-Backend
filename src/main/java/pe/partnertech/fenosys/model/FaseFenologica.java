/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fasefenologica")
public class FaseFenologica implements Serializable {

    private static final long serialVersionUID = 1L;

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fasefenologica")
    private Long idFaseFenologica;

    @Column(name = "nombre_fasefenologica")
    private String nombreFaseFenologica;

    @Column(name = "nivel_fasefenologica")
    private int nivelFaseFenologica;

    //Constructores
    public FaseFenologica() {
    }

    public FaseFenologica(String nombreFaseFenologica, int nivelFaseFenologica) {
        this.nombreFaseFenologica = nombreFaseFenologica;
        this.nivelFaseFenologica = nivelFaseFenologica;
    }

    //Getters y Setters
    public Long getIdFaseFenologica() {
        return idFaseFenologica;
    }

    public void setIdFaseFenologica(Long idFaseFenologica) {
        this.idFaseFenologica = idFaseFenologica;
    }

    public String getNombreFaseFenologica() {
        return nombreFaseFenologica;
    }

    public void setNombreFaseFenologica(String nombreFaseFenologica) {
        this.nombreFaseFenologica = nombreFaseFenologica;
    }

    public int getNivelFaseFenologica() {
        return nivelFaseFenologica;
    }

    public void setNivelFaseFenologica(int nivelFaseFenologica) {
        this.nivelFaseFenologica = nivelFaseFenologica;
    }
}
