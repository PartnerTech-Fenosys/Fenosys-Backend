/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.controller.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.partnertech.fenosys.dto.response.general.ubicacion.DepartamentoResponse;
import pe.partnertech.fenosys.dto.response.general.ubicacion.DistritoResponse;
import pe.partnertech.fenosys.dto.response.general.ubicacion.PaisResponse;
import pe.partnertech.fenosys.dto.response.general.ubicacion.ProvinciaResponse;
import pe.partnertech.fenosys.model.Departamento;
import pe.partnertech.fenosys.model.Pais;
import pe.partnertech.fenosys.model.Provincia;
import pe.partnertech.fenosys.service.IDepartamentoService;
import pe.partnertech.fenosys.service.IDistritoService;
import pe.partnertech.fenosys.service.IPaisService;
import pe.partnertech.fenosys.service.IProvinciaService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UbicacionController {

    final
    IPaisService paisService;

    final
    IDepartamentoService departamentoService;

    final
    IProvinciaService provinciaService;

    final
    IDistritoService distritoService;

    public UbicacionController(IPaisService paisService, IDepartamentoService departamentoService,
                               IProvinciaService provinciaService, IDistritoService distritoService) {
        this.paisService = paisService;
        this.departamentoService = departamentoService;
        this.provinciaService = provinciaService;
        this.distritoService = distritoService;
    }

    @GetMapping("/ubicacion/show/paises")
    public ResponseEntity<?> MostrarPaises() {

        Set<PaisResponse> lista_paises = new HashSet<>();

        paisService.MostrarPaises().forEach(
                pais -> lista_paises.add(
                        new PaisResponse(
                                pais.getIdPais(),
                                pais.getNombrePais()
                        )));

        return new ResponseEntity<>(lista_paises, HttpStatus.OK);
    }

    @GetMapping("/ubicacion/show/departamentos")
    public ResponseEntity<?> MostrarDepartamentos() {

        Set<DepartamentoResponse> lista_departamentos = new HashSet<>();

        departamentoService.MostrarDepartamentos().forEach(
                departamento -> lista_departamentos.add(
                        new DepartamentoResponse(
                                BuscarPaisByDepartamento(departamento.getIdDepartamento()),
                                departamento.getIdDepartamento(),
                                departamento.getNombreDepartamento()
                        )));

        return new ResponseEntity<>(lista_departamentos, HttpStatus.OK);
    }

    @GetMapping("/ubicacion/find/departamentos/from_pais/{id_pais}")
    public ResponseEntity<?> BuscarDepartamentosPorPais(@PathVariable("id_pais") Long id_pais) {

        Set<DepartamentoResponse> lista_departamentos = new HashSet<>();

        departamentoService.BuscarDepartamentos_By_IDPais(id_pais).forEach(
                departamento -> lista_departamentos.add(
                        new DepartamentoResponse(
                                BuscarPaisByDepartamento(departamento.getIdDepartamento()),
                                departamento.getIdDepartamento(),
                                departamento.getNombreDepartamento()
                        )));

        return new ResponseEntity<>(lista_departamentos, HttpStatus.OK);
    }

    Long BuscarPaisByDepartamento(Long id_departamento) {

        Optional<Pais> pais_data = paisService.BuscarPais_By_IDDepartamento(id_departamento);

        if (pais_data.isPresent()) {
            Pais pais = pais_data.get();

            return pais.getIdPais();
        } else {
            return null;
        }
    }

    @GetMapping("/ubicacion/show/provincias")
    public ResponseEntity<?> MostrarProvincias() {

        Set<ProvinciaResponse> lista_provincias = new HashSet<>();

        provinciaService.MostrarProvincias().forEach(
                provincia -> lista_provincias.add(
                        new ProvinciaResponse(
                                BuscarDepartamentoByProvincia(provincia.getIdProvincia()),
                                provincia.getIdProvincia(),
                                provincia.getNombreProvincia()
                        )));

        return new ResponseEntity<>(lista_provincias, HttpStatus.OK);
    }

    @GetMapping("/ubicacion/find/provincias/from_departamento/{id_departamento}")
    public ResponseEntity<?> BuscarProvinciasPorDepartamento(@PathVariable("id_departamento") Long id_departamento) {

        Set<ProvinciaResponse> lista_provincias = new HashSet<>();

        provinciaService.BuscarProvincias_IDDepartamento(id_departamento).forEach(
                provincia -> lista_provincias.add(
                        new ProvinciaResponse(
                                BuscarDepartamentoByProvincia(provincia.getIdProvincia()),
                                provincia.getIdProvincia(),
                                provincia.getNombreProvincia()
                        )));

        return new ResponseEntity<>(lista_provincias, HttpStatus.OK);
    }

    Long BuscarDepartamentoByProvincia(Long id_provincia) {

        Optional<Departamento> departamento_data = departamentoService.BuscarDepartamento_By_IDProvincia(id_provincia);

        if (departamento_data.isPresent()) {
            Departamento departamento = departamento_data.get();

            return departamento.getIdDepartamento();
        } else {
            return null;
        }
    }

    @GetMapping("/ubicacion/show/distritos")
    public ResponseEntity<?> MostrarDistritos() {

        Set<DistritoResponse> lista_distritos = new HashSet<>();

        distritoService.MostrarDistritos().forEach(
                distrito -> lista_distritos.add(
                        new DistritoResponse(
                                BuscarProvinciaByDistrito(distrito.getIdDistrito()),
                                distrito.getIdDistrito(),
                                distrito.getNombreDistrito()
                        )));

        return new ResponseEntity<>(lista_distritos, HttpStatus.OK);
    }

    @GetMapping("/ubicacion/find/distritos/from_provincia/{id_provincia}")
    public ResponseEntity<?> BuscarDistritosPorProvincia(@PathVariable("id_provincia") Long id_provincia) {

        Set<DistritoResponse> lista_distritos = new HashSet<>();

        distritoService.BuscarDistritos_By_IDProvincia(id_provincia).forEach(
                distrito -> lista_distritos.add(
                        new DistritoResponse(
                                BuscarProvinciaByDistrito(distrito.getIdDistrito()),
                                distrito.getIdDistrito(),
                                distrito.getNombreDistrito()
                        )));

        return new ResponseEntity<>(lista_distritos, HttpStatus.OK);
    }

    Long BuscarProvinciaByDistrito(Long id_distrito) {

        Optional<Provincia> provincia_data = provinciaService.BuscarProvincia_IDDistrito(id_distrito);

        if (provincia_data.isPresent()) {
            Provincia provincia = provincia_data.get();

            return provincia.getIdProvincia();
        } else {
            return null;
        }
    }
}
