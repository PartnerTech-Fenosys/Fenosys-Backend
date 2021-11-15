/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "distrito")
public class Distrito implements Serializable {

    private static final long serialVersionUID = 1L;

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_distrito")
    private Long idDistrito;

    @Column(name = "nombre_distrito")
    private String nombreDistrito;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "distritoUsuario")
    private Set<Usuario> usuariosDistrito;

    @ManyToOne
    @JoinTable(name = "provincia_distritos",
            joinColumns = @JoinColumn(name = "id_distrito", referencedColumnName = "id_distrito"),
            inverseJoinColumns = @JoinColumn(name = "id_provincia", referencedColumnName = "id_provincia"))
    private Provincia provinciaDistrito;

    //Constructores
    public Distrito() {
    }

    public Distrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    //Getters y Setters
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

    public Set<Usuario> getUsuariosDistrito() {
        return usuariosDistrito;
    }

    public void setUsuariosDistrito(Set<Usuario> usuariosDistrito) {
        this.usuariosDistrito = usuariosDistrito;
    }

    public Provincia getProvinciaDistrito() {
        return provinciaDistrito;
    }

    public void setProvinciaDistrito(Provincia provinciaDistrito) {
        this.provinciaDistrito = provinciaDistrito;
    }
}
