/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.service;

import pe.partnertech.fenosys.enums.RolNombre;
import pe.partnertech.fenosys.model.Rol;

import java.util.Optional;

public interface IRolService {

    Optional<Rol> BuscarRol_Nombre(RolNombre rol);
}
