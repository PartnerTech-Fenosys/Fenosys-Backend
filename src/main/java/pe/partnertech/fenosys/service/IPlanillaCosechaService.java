/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.service;

import pe.partnertech.fenosys.model.PlanillaCosecha;

import java.util.Optional;

public interface IPlanillaCosechaService {

    //PCU: Planilla Cosecha de Usuario

    Optional<PlanillaCosecha> BuscarPlanillaCosecha_By_IDPlanillaCosecha(Long id_planillacosecha);

    Optional<PlanillaCosecha> BuscarPlanillaCosecha_By_NombrePlanillaCosechaAndIDAgricultor(String nombre_planillacosecha,
                                                                                            Long id_agricultor);

    boolean ValidarPlanillaCosecha_By_NombrePlanillaCosechaAndIDAgricultor(String nombre_planillacosecha,
                                                                           Long id_agricultor);

    void GuardarPlanillaCosecha(PlanillaCosecha planillacosecha);

    void EliminarPlanillaCosecha_From_PCU_MiddleTable(Long id_planillacosecha);

    void EliminarPlanillaCosecha_This(Long id_planillacosecha);
}
