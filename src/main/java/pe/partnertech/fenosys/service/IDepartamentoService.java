/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.service;

import pe.partnertech.fenosys.model.Departamento;

import java.util.Optional;
import java.util.Set;

public interface IDepartamentoService {

    Set<Departamento> MostrarDepartamentos();

    Set<Departamento> BuscarDepartamentos_By_IDPais(Long id_pais);

    Optional<Departamento> BuscarDepartamento_By_IDProvincia(Long id_provincia);
}
