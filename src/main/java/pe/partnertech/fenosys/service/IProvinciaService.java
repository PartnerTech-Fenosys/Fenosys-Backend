/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.service;

import pe.partnertech.fenosys.model.Provincia;

import java.util.Optional;
import java.util.Set;

public interface IProvinciaService {

    Set<Provincia> MostrarProvincias();

    Set<Provincia> BuscarProvincias_IDDepartamento(Long id_departamento);

    Optional<Provincia> BuscarProvincia_IDDistrito(Long id_distrito);
}
