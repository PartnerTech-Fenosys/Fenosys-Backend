/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "registrodiario")
public class RegistroDiario implements Serializable {

    private static final long serialVersionUID = 1L;

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registrodiario")
    private Long idRegistroDiario;

    @Column(name = "fecha_registrodiario")
    private LocalDateTime fechaRegistroDiario;

    //Constructores
    public RegistroDiario() {
    }

    public RegistroDiario(LocalDateTime fechaRegistroDiario) {
        this.fechaRegistroDiario = fechaRegistroDiario;
    }

    //Getters y Setters
    public Long getIdRegistroDiario() {
        return idRegistroDiario;
    }

    public void setIdRegistroDiario(Long idRegistroDiario) {
        this.idRegistroDiario = idRegistroDiario;
    }

    public LocalDateTime getFechaRegistroDiario() {
        return fechaRegistroDiario;
    }

    public void setFechaRegistroDiario(LocalDateTime fechaRegistroDiario) {
        this.fechaRegistroDiario = fechaRegistroDiario;
    }
}
