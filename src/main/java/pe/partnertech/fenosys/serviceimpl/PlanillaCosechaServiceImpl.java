/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.serviceimpl;

import org.springframework.stereotype.Service;
import pe.partnertech.fenosys.model.PlanillaCosecha;
import pe.partnertech.fenosys.repository.IPlanillaCosechaDAO;
import pe.partnertech.fenosys.service.IPlanillaCosechaService;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PlanillaCosechaServiceImpl implements IPlanillaCosechaService {

    //PCU: Planilla Cosecha de Usuario

    final
    IPlanillaCosechaDAO data;

    public PlanillaCosechaServiceImpl(IPlanillaCosechaDAO data) {
        this.data = data;
    }

    @Override
    public Optional<PlanillaCosecha> BuscarPlanillaCosecha_By_IDPlanillaCosecha(Long id_planillacosecha) {
        return data.findById(id_planillacosecha);
    }

    @Override
    public Optional<PlanillaCosecha> BuscarPlanillaCosecha_By_NombrePlanillaCosechaAndIDAgricultor(String nombre_planillacosecha, Long id_agricultor) {
        return data.findPlanillaCosechaByNombrePlanillaCosechaAndIDAgricultor(nombre_planillacosecha, id_agricultor);
    }

    @Override
    public boolean ValidarPlanillaCosecha_By_NombrePlanillaCosechaAndIDAgricultor(String nombre_planillacosecha,
                                                                                  Long id_agricultor) {
        return data.existsNombrePlanillaCosechaAndIDAgricultor(nombre_planillacosecha, id_agricultor);
    }

    @Override
    public void GuardarPlanillaCosecha(PlanillaCosecha planillacosecha) {
        data.save(planillacosecha);
    }

    @Override
    public void EliminarPlanillaCosecha_From_PCU_MiddleTable(Long id_planillacosecha) {
        data.deletePlanillaCosechaFromPCUMiddleTable(id_planillacosecha);
    }

    @Override
    public void EliminarPlanillaCosecha_This(Long id_planillacosecha) {
        data.deleteById(id_planillacosecha);
    }
}
