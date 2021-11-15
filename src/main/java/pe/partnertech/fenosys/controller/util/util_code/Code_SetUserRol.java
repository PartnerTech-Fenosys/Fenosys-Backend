/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.controller.util.util_code;

import pe.partnertech.fenosys.model.Rol;
import pe.partnertech.fenosys.model.Usuario;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Code_SetUserRol {

    public static void SetUserRol(Usuario usuario, Optional<Rol> rol_data) {
        if (rol_data.isPresent()) {
            Set<Rol> roles = new HashSet<>();
            roles.add(rol_data.get());
            usuario.setRolesUsuario(roles);
        }
    }
}
