/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.service;

import pe.partnertech.fenosys.model.Usuario;

import java.util.Optional;

public interface IUsuarioService {

    Optional<Usuario> BuscarUsuario_By_IDUsuario(Long id_usuario);

    Optional<Usuario> BuscarUsuario_By_EmailUsuario(String email_usuario);

    Optional<Usuario> BuscarUsuario_By_UsernameOrEmail(String username_or_email);

    Optional<Usuario> BuscarUsuario_By_IDUtilityToken(Long id_utilitytoken);

    Boolean ValidarUsername(String username_usuario);

    void GuardarUsuario(Usuario usuario);

    void EliminarUsuario(Long id_usuario);
}
