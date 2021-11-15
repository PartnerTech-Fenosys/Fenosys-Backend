/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.partnertech.fenosys.model.FaseFenologica;

import java.util.Set;

@Repository
public interface IFaseFenologicaDAO extends JpaRepository<FaseFenologica, Long> {

    //FFPC: Fases Fenológicas de Planilla de Cosecha

    @Query(value = "SELECT ffpc.*, ff.*, pc.* " +
            "FROM fasesfenologicas_planillacosecha ffpc " +
            "JOIN fasefenologica ff ON ffpc.id_fasefenologica = ff.id_fasefenologica " +
            "JOIN planillacosecha pc ON ffpc.id_planillacosecha = pc.id_planillacosecha " +
            "WHERE pc.id_planillacosecha = ?1", nativeQuery = true)
    Set<FaseFenologica> findFasesFenologicas_From_FFPC_MiddleTable_By_IDPlanillaCosecha(Long id_planillacosecha);

    @Query(value = "DELETE " +
            "FROM fasesfenologicas_planillacosecha ffpc " +
            "WHERE ffpc.id_planillacosecha = ?1", nativeQuery = true)
    @Modifying
    void deleteFaseFenologica_From_FFPC_MiddleTable_By_IDPlanillaCosecha(Long id_planillacosecha);
}
