/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.serviceimpl;

import org.springframework.stereotype.Service;
import pe.partnertech.fenosys.model.FaseFenologica;
import pe.partnertech.fenosys.repository.IFaseFenologicaDAO;
import pe.partnertech.fenosys.service.IFaseFenologicaService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class FaseFenologicaServiceImpl implements IFaseFenologicaService {

    final
    IFaseFenologicaDAO data;

    //FFPC: Fases Fenológicas de Planilla de Cosecha

    public FaseFenologicaServiceImpl(IFaseFenologicaDAO data) {
        this.data = data;
    }

    @Override
    public Set<FaseFenologica> BuscarFasesFenologicas_From_FFPC_MiddleTable_By_IDPlanillaCosecha(Long id_planillacosecha) {
        return data.findFasesFenologicas_From_FFPC_MiddleTable_By_IDPlanillaCosecha(id_planillacosecha);
    }

    @Override
    public void GuardarFasesFenologicas_Config(List<FaseFenologica> lista_fasesfenologicas) {
        data.saveAll(lista_fasesfenologicas);
    }

    @Override
    public void EliminarFaseFenologica_From_FFPC_MiddleTable_By_IDPlanillaCosecha(Long id_planillacosecha) {
        data.deleteFaseFenologica_From_FFPC_MiddleTable_By_IDPlanillaCosecha(id_planillacosecha);
    }

    @Override
    public void EliminarFaseFenologica(Long id_fasefenologica) {
        data.deleteById(id_fasefenologica);
    }
}
