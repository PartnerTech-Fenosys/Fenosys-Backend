/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "planillacosecha")
public class PlanillaCosecha implements Serializable {

    private static final long serialVersionUID = 1L;

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_planillacosecha")
    private Long idPlanillaCosecha;

    @Column(name = "cosecha_planillacosecha")
    private String nombreCosecha;

    @Column(name = "tipo_planillacosecha")
    private String tipoCosecha;

    @Column(name = "next_registrodiario")
    private LocalDateTime nextRegistroDiario;

    @OneToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH
    }, fetch = FetchType.LAZY)
    @JoinTable(name = "fasesfenologicas_planillacosecha",
            joinColumns = @JoinColumn(name = "id_planillacosecha", referencedColumnName = "id_planillacosecha"),
            inverseJoinColumns = @JoinColumn(name = "id_fasefenologica", referencedColumnName = "id_fasefenologica"))
    private Set<FaseFenologica> fasesfenologicasPlanillaCosecha;

    @OneToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH
    }, fetch = FetchType.LAZY)
    @JoinTable(name = "registrosdiarios_planillacosecha",
            joinColumns = @JoinColumn(name = "id_planillacosecha", referencedColumnName = "id_planillacosecha"),
            inverseJoinColumns = @JoinColumn(name = "id_registrodiario", referencedColumnName = "id_registrodiario"))
    private Set<RegistroDiario> registrosdiariosPlanillaCosecha;

    //Constructores
    public PlanillaCosecha() {
    }

    public PlanillaCosecha(String nombreCosecha) {
        this.nombreCosecha = nombreCosecha;
    }

    //Getters y Setters
    public Long getIdPlanillaCosecha() {
        return idPlanillaCosecha;
    }

    public void setIdPlanillaCosecha(Long idPlanillaCosecha) {
        this.idPlanillaCosecha = idPlanillaCosecha;
    }

    public String getNombreCosecha() {
        return nombreCosecha;
    }

    public void setNombreCosecha(String nombreCosecha) {
        this.nombreCosecha = nombreCosecha;
    }

    public String getTipoCosecha() {
        return tipoCosecha;
    }

    public void setTipoCosecha(String tipoCosecha) {
        this.tipoCosecha = tipoCosecha;
    }

    public LocalDateTime getNextRegistroDiario() {
        return nextRegistroDiario;
    }

    public void setNextRegistroDiario(LocalDateTime nextRegistroDiario) {
        this.nextRegistroDiario = nextRegistroDiario;
    }

    public Set<FaseFenologica> getFasesfenologicasPlanillaCosecha() {
        return fasesfenologicasPlanillaCosecha;
    }

    public void setFasesfenologicasPlanillaCosecha(Set<FaseFenologica> fasesfenologicasPlanillaCosecha) {
        this.fasesfenologicasPlanillaCosecha = fasesfenologicasPlanillaCosecha;
    }

    public Set<RegistroDiario> getRegistrosdiariosPlanillaCosecha() {
        return registrosdiariosPlanillaCosecha;
    }

    public void setRegistrosdiariosPlanillaCosecha(Set<RegistroDiario> registrosdiariosPlanillaCosecha) {
        this.registrosdiariosPlanillaCosecha = registrosdiariosPlanillaCosecha;
    }
}
