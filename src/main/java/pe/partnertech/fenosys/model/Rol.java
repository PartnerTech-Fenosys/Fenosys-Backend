/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.model;

import pe.partnertech.fenosys.enums.RolNombre;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "rol")
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private int idRol;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", length = 20)
    private RolNombre nombreRol;

    @ManyToMany(mappedBy = "rolesUsuario")
    Set<Usuario> usuariosRoles;

    //Constructores
    public Rol() {
    }

    public Rol(RolNombre nombreRol) {
        this.nombreRol = nombreRol;
    }

    //Getters y Setters
    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public RolNombre getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(RolNombre nombreRol) {
        this.nombreRol = nombreRol;
    }

    public Set<Usuario> getUsuariosRoles() {
        return usuariosRoles;
    }

    public void setUsuariosRoles(Set<Usuario> usuariosRoles) {
        this.usuariosRoles = usuariosRoles;
    }
}
