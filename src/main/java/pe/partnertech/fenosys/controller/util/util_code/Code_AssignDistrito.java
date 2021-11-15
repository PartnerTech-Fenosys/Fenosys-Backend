/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.controller.util.util_code;

import pe.partnertech.fenosys.model.Distrito;
import pe.partnertech.fenosys.model.Usuario;
import pe.partnertech.fenosys.service.IUsuarioService;

public class Code_AssignDistrito {

    public static void AgregarUsuarioToDistrito(Usuario usuario, Distrito distrito, IUsuarioService usuarioService) {

        usuario.setDistritoUsuario(distrito);
        usuarioService.GuardarUsuario(usuario);
    }
}
