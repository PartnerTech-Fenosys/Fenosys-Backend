/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.controller.gestion_fenologica;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.partnertech.fenosys.dto.response.general.MessageResponse;
import pe.partnertech.fenosys.model.FaseFenologica;
import pe.partnertech.fenosys.model.PlanillaCosecha;
import pe.partnertech.fenosys.model.Usuario;
import pe.partnertech.fenosys.service.IFaseFenologicaService;
import pe.partnertech.fenosys.service.IPlanillaCosechaService;
import pe.partnertech.fenosys.service.IUsuarioService;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PlanillaCosechaController {

    final
    IPlanillaCosechaService planillaCosechaService;

    final
    IUsuarioService usuarioService;

    final
    IFaseFenologicaService faseFenologicaService;

    public PlanillaCosechaController(IPlanillaCosechaService planillaCosechaService, IUsuarioService usuarioService,
                                     IFaseFenologicaService faseFenologicaService) {
        this.planillaCosechaService = planillaCosechaService;
        this.usuarioService = usuarioService;
        this.faseFenologicaService = faseFenologicaService;
    }

    @PostMapping("/agricultor/{id_agricultor}/planilla/register")
    @PreAuthorize("hasRole('ROLE_AGRICULTOR')")
    public ResponseEntity<?> RegistrarPlanillaCosecha(@PathVariable("id_agricultor") Long id_agricultor,
                                                      @RequestBody PlanillaCosecha planillacosecha) {

        Optional<Usuario> agricultor_data = usuarioService.BuscarUsuario_By_IDUsuario(id_agricultor);

        if (agricultor_data.isPresent()) {
            if (planillaCosechaService.ValidarPlanillaCosecha_By_NombrePlanillaCosechaAndIDAgricultor(
                    planillacosecha.getNombreCosecha(), id_agricultor)) {
                return new ResponseEntity<>(new MessageResponse("Ya se encuentra registrado esa planilla."),
                        HttpStatus.CONFLICT);
            } else {
                Usuario agricultor = agricultor_data.get();

                PlanillaCosecha planillacosecha_agricultor = new PlanillaCosecha();

                planillacosecha_agricultor.setNombreCosecha(planillacosecha.getNombreCosecha());

                planillaCosechaService.GuardarPlanillaCosecha(planillacosecha_agricultor);

                Set<PlanillaCosecha> planillacosecha_list;
                if (agricultor.getPlanillacosechaUsuario() == null) {
                    planillacosecha_list = new HashSet<>();
                } else {
                    planillacosecha_list = agricultor.getPlanillacosechaUsuario();
                }
                planillacosecha_list.add(planillacosecha_agricultor);

                agricultor.setPlanillacosechaUsuario(planillacosecha_list);

                usuarioService.GuardarUsuario(agricultor);

                Optional<PlanillaCosecha> planillacosecha_data =
                        planillaCosechaService.BuscarPlanillaCosecha_By_NombrePlanillaCosechaAndIDAgricultor(
                                planillacosecha.getNombreCosecha(), id_agricultor);

                if (planillacosecha_data.isPresent()) {
                    PlanillaCosecha config_planillacosecha = planillacosecha_data.get();

                    switch (planillacosecha_agricultor.getNombreCosecha()) {
                        case "Avena":
                            config_planillacosecha.setTipoCosecha("Anual");
                            GuardarConfiguracionAvena(config_planillacosecha);
                            break;
                        case "Caña de Azúcar":
                            config_planillacosecha.setTipoCosecha("Permanente");
                            GuardarConfiguracionAzucar(config_planillacosecha);
                            break;
                        case "Kiwicha":
                            config_planillacosecha.setTipoCosecha("Anual");
                            GuardarConfiguracionKiwicha(config_planillacosecha);
                            break;
                        case "Mango":
                            config_planillacosecha.setTipoCosecha("Permanente");
                            GuardarConfiguracionMango(config_planillacosecha);
                            break;
                        case "Limón":
                            config_planillacosecha.setTipoCosecha("Permanente");
                            GuardarConfiguracionLimon(config_planillacosecha);
                            break;
                        default:
                            config_planillacosecha.setTipoCosecha(null);
                    }

                    planillaCosechaService.GuardarPlanillaCosecha(config_planillacosecha);

                    return new ResponseEntity<>(new MessageResponse("Se ha guardado la planilla satisfactoriamente."),
                            HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new MessageResponse("Ocurrió un error durante la configuración de la Planilla."),
                            HttpStatus.BAD_REQUEST);
                }
            }
        } else {
            return new ResponseEntity<>(new MessageResponse("No se encontró información del usuario."),
                    HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/planilla/{id_planillacosecha}/delete")
    @PreAuthorize("hasRole('ROLE_AGRICULTOR')")
    public ResponseEntity<?> EliminarPlanillaCosecha(@PathVariable("id_planillacosecha") Long id_planillacosecha) {

        Optional<PlanillaCosecha> planillacosecha_data = planillaCosechaService.BuscarPlanillaCosecha_By_IDPlanillaCosecha(id_planillacosecha);

        if (planillacosecha_data.isPresent()) {

            EliminarFasesFenologicas(id_planillacosecha);

            planillaCosechaService.EliminarPlanillaCosecha_From_PCU_MiddleTable(id_planillacosecha);
            planillaCosechaService.EliminarPlanillaCosecha_This(id_planillacosecha);

            return new ResponseEntity<>(new MessageResponse("Se ha eliminado la planilla satisfactoriamente."),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("Ocurrió un error al eliminar la planilla."),
                    HttpStatus.NOT_FOUND);
        }
    }

    private void FasesFenologicasT1(PlanillaCosecha planillaCosecha, FaseFenologica ff_01, FaseFenologica ff_02,
                                    FaseFenologica ff_03, FaseFenologica ff_04, FaseFenologica ff_05,
                                    FaseFenologica ff_06, FaseFenologica ff_07, FaseFenologica ff_08,
                                    FaseFenologica ff_09) {
        List<FaseFenologica> aux_lista_fasesfenologicas = Arrays.asList(ff_01, ff_02, ff_03, ff_04, ff_05,
                ff_06, ff_07, ff_08, ff_09);
        faseFenologicaService.GuardarFasesFenologicas_Config(aux_lista_fasesfenologicas);

        Set<FaseFenologica> lista_fasesfenologicas = new HashSet<>(aux_lista_fasesfenologicas);
        planillaCosecha.setFasesfenologicasPlanillaCosecha(lista_fasesfenologicas);
        planillaCosechaService.GuardarPlanillaCosecha(planillaCosecha);
    }

    private void FasesFenologicasT2(PlanillaCosecha planillaCosecha, FaseFenologica ff_01, FaseFenologica ff_02,
                                    FaseFenologica ff_03) {
        FaseFenologica ff_04 = new FaseFenologica("Fructificación", 4);

        FaseFenologica ff_05 = new FaseFenologica("Maduración", 5);

        List<FaseFenologica> aux_lista_fasesfenologicas = Arrays.asList(ff_01, ff_02, ff_03, ff_04, ff_05);
        faseFenologicaService.GuardarFasesFenologicas_Config(aux_lista_fasesfenologicas);

        Set<FaseFenologica> lista_fasesfenologicas = new HashSet<>(aux_lista_fasesfenologicas);
        planillaCosecha.setFasesfenologicasPlanillaCosecha(lista_fasesfenologicas);
        planillaCosechaService.GuardarPlanillaCosecha(planillaCosecha);
    }

    //Avena Config
    public void GuardarConfiguracionAvena(PlanillaCosecha planillaCosecha) {
        FaseFenologica ff_01 = new FaseFenologica("Emergencia", 1);

        FaseFenologica ff_02 = new FaseFenologica("Tercera Hoja", 2);

        FaseFenologica ff_03 = new FaseFenologica("Macollaje", 3);

        FaseFenologica ff_04 = new FaseFenologica("Encañado", 4);

        FaseFenologica ff_05 = new FaseFenologica("Panoja", 5);

        FaseFenologica ff_06 = new FaseFenologica("Floración", 6);

        FaseFenologica ff_07 = new FaseFenologica("Maduración lechosa", 7);

        FaseFenologica ff_08 = new FaseFenologica("Maduración pastosa", 8);

        FaseFenologica ff_09 = new FaseFenologica("Maduración córnea", 9);

        FasesFenologicasT1(planillaCosecha, ff_01, ff_02, ff_03, ff_04, ff_05, ff_06, ff_07, ff_08, ff_09);
    }

    //Kiwicha Config
    public void GuardarConfiguracionKiwicha(PlanillaCosecha planillaCosecha) {
        FaseFenologica ff_01 = new FaseFenologica("Emergencia", 1);

        FaseFenologica ff_02 = new FaseFenologica("Dos hojas verdaderas", 2);

        FaseFenologica ff_03 = new FaseFenologica("Seis hojas verdaderas", 3);

        FaseFenologica ff_04 = new FaseFenologica("Ramificación", 4);

        FaseFenologica ff_05 = new FaseFenologica("Panoja", 5);

        FaseFenologica ff_06 = new FaseFenologica("Floración", 6);

        FaseFenologica ff_07 = new FaseFenologica("Grano lechoso", 7);

        FaseFenologica ff_08 = new FaseFenologica("Grano pastoso", 8);

        FaseFenologica ff_09 = new FaseFenologica("Maduración", 9);

        FasesFenologicasT1(planillaCosecha, ff_01, ff_02, ff_03, ff_04, ff_05, ff_06, ff_07, ff_08, ff_09);
    }

    //Limon Config
    public void GuardarConfiguracionLimon(PlanillaCosecha planillaCosecha) {
        FaseFenologica ff_01 = new FaseFenologica("Hinchazón de botón floral", 1);

        FaseFenologica ff_02 = new FaseFenologica("Apertura de botón floral", 2);

        FaseFenologica ff_03 = new FaseFenologica("Floración", 3);

        FasesFenologicasT2(planillaCosecha, ff_01, ff_02, ff_03);
    }

    //Caña de Azucar Config
    public void GuardarConfiguracionAzucar(PlanillaCosecha planillaCosecha) {
        FaseFenologica ff_01 = new FaseFenologica("Emergencia", 1);

        FaseFenologica ff_02 = new FaseFenologica("Primer banderín", 2);

        FaseFenologica ff_03 = new FaseFenologica("Macollaje", 3);

        FaseFenologica ff_04 = new FaseFenologica("Crecimiento de tallos", 4);

        FaseFenologica ff_05 = new FaseFenologica("Inflorescencia", 5);

        FaseFenologica ff_06 = new FaseFenologica("Floración", 6);

        FaseFenologica ff_07 = new FaseFenologica("Maduración", 7);

        List<FaseFenologica> aux_lista_fasesfenologicas = Arrays.asList(ff_01, ff_02, ff_03, ff_04, ff_05, ff_06, ff_07);
        faseFenologicaService.GuardarFasesFenologicas_Config(aux_lista_fasesfenologicas);

        Set<FaseFenologica> lista_fasesfenologicas = new HashSet<>(aux_lista_fasesfenologicas);
        planillaCosecha.setFasesfenologicasPlanillaCosecha(lista_fasesfenologicas);
        planillaCosechaService.GuardarPlanillaCosecha(planillaCosecha);
    }

    //Mango Config
    public void GuardarConfiguracionMango(PlanillaCosecha planillaCosecha) {
        FaseFenologica ff_01 = new FaseFenologica("Brotamiento", 1);

        FaseFenologica ff_02 = new FaseFenologica("Floración", 2);

        FaseFenologica ff_03 = new FaseFenologica("Cuajado", 3);

        FasesFenologicasT2(planillaCosecha, ff_01, ff_02, ff_03);
    }

    //Eliminar las Fases Fenologicas de la Planilla de Cosecha
    void EliminarFasesFenologicas(Long idPlanillaCosecha) {

        Set<FaseFenologica> lista_fasesfenologicas =
                faseFenologicaService.BuscarFasesFenologicas_From_FFPC_MiddleTable_By_IDPlanillaCosecha(idPlanillaCosecha);

        for (FaseFenologica delete_fasefenologica : lista_fasesfenologicas) {
            faseFenologicaService.EliminarFaseFenologica_From_FFPC_MiddleTable_By_IDPlanillaCosecha(idPlanillaCosecha);
            faseFenologicaService.EliminarFaseFenologica(delete_fasefenologica.getIdFaseFenologica());
        }
    }
}

