/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.service;

import pe.partnertech.fenosys.model.Pais;

import java.util.Optional;
import java.util.Set;

public interface IPaisService {

    Set<Pais> MostrarPaises();

    Optional<Pais> BuscarPais_By_IDDepartamento(Long id_departamento);
}
