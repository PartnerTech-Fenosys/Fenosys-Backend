/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.partnertech.fenosys.model.PlanillaCosecha;

import java.util.Optional;

@Repository
public interface IPlanillaCosechaDAO extends JpaRepository<PlanillaCosecha, Long> {

    //PCU: Planilla Cosecha de Usuario

    @Query(value = "SELECT pcu.*, pc.*, u.* " +
            "FROM planillascosecha_usuario pcu " +
            "JOIN planillacosecha pc ON pcu.id_planillacosecha = pc.id_planillacosecha " +
            "JOIN usuario u ON pcu.id_usuario = u.id_usuario " +
            "WHERE pc.cosecha_planillacosecha LIKE ?1 AND u.id_usuario = ?2", nativeQuery = true)
    boolean existsNombrePlanillaCosechaAndIDAgricultor(String nombre_planillacosecha,
                                                       Long id_agricultor);

    @Query(value = "SELECT pcu.*, pc.*, u.* " +
            "FROM planillascosecha_usuario pcu " +
            "JOIN planillacosecha pc ON pcu.id_planillacosecha = pc.id_planillacosecha " +
            "JOIN usuario u ON pcu.id_usuario = u.id_usuario " +
            "WHERE pc.cosecha_planillacosecha LIKE ?1 AND u.id_usuario = ?2", nativeQuery = true)
    Optional<PlanillaCosecha> findPlanillaCosechaByNombrePlanillaCosechaAndIDAgricultor(String nombre_planillacosecha,
                                                                                        Long id_agricultor);

    @Query(value = "DELETE " +
            "FROM planillascosecha_usuario pcu " +
            "WHERE pcu.id_planillacosecha = ?1", nativeQuery = true)
    @Modifying
    void deletePlanillaCosechaFromPCUMiddleTable(Long id_planillacosecha);
}
