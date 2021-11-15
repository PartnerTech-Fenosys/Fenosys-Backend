/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "provincia")
public class Provincia implements Serializable {

    private static final long serialVersionUID = 1L;

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_provincia")
    private Long idProvincia;

    @Column(name = "nombre_provincia")
    private String nombreProvincia;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "provinciaDistrito")
    private Set<Distrito> distritosProvincia;

    @ManyToOne
    @JoinTable(name = "departamento_provincias",
            joinColumns = @JoinColumn(name = "id_provincia", referencedColumnName = "id_provincia"),
            inverseJoinColumns = @JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento"))
    private Departamento departamentoProvincia;

    //Constructores
    public Provincia() {
    }

    public Provincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    //Getters y Setters
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

    public Set<Distrito> getDistritosProvincia() {
        return distritosProvincia;
    }

    public void setDistritosProvincia(Set<Distrito> distritosProvincia) {
        this.distritosProvincia = distritosProvincia;
    }

    public Departamento getDepartamentoProvincia() {
        return departamentoProvincia;
    }

    public void setDepartamentoProvincia(Departamento departamentoProvincia) {
        this.departamentoProvincia = departamentoProvincia;
    }
}
