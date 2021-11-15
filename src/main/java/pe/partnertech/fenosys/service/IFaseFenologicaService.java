/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.service;

import pe.partnertech.fenosys.model.FaseFenologica;

import java.util.List;
import java.util.Set;

public interface IFaseFenologicaService {

    //FFPC: Fases Fenológicas de Planilla de Cosecha

    Set<FaseFenologica> BuscarFasesFenologicas_From_FFPC_MiddleTable_By_IDPlanillaCosecha(Long id_planillacosecha);

    void GuardarFasesFenologicas_Config(List<FaseFenologica> lista_fasesfenologicas);

    void EliminarFaseFenologica_From_FFPC_MiddleTable_By_IDPlanillaCosecha(Long id_planillacosecha);

    void EliminarFaseFenologica(Long id_fasefenologica);
}
