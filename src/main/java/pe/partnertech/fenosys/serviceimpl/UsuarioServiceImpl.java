/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.serviceimpl;

import org.springframework.stereotype.Service;
import pe.partnertech.fenosys.model.Usuario;
import pe.partnertech.fenosys.repository.IUsuarioDAO;
import pe.partnertech.fenosys.service.IUsuarioService;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServiceImpl implements IUsuarioService {

    final
    IUsuarioDAO data;

    public UsuarioServiceImpl(IUsuarioDAO data) {
        this.data = data;
    }

    @Override
    public Optional<Usuario> BuscarUsuario_By_IDUsuario(Long id_usuario) {
        return data.findById(id_usuario);
    }

    @Override
    public Optional<Usuario> BuscarUsuario_By_EmailUsuario(String email_usuario) {
        return data.findByEmailUsuario(email_usuario);
    }

    @Override
    public Optional<Usuario> BuscarUsuario_By_UsernameOrEmail(String username_or_email) {
        return data.findByUsernameOrEmail(username_or_email);
    }

    @Override
    public Optional<Usuario> BuscarUsuario_By_IDUtilityToken(Long id_utilitytoken) {
        return data.findByIdUtilityToken(id_utilitytoken);
    }

    @Override
    public Boolean ValidarUsername(String username_usuario) {
        return data.existsByUsernameUsuario(username_usuario);
    }

    @Override
    public void GuardarUsuario(Usuario usuario) {
        data.save(usuario);
    }

    @Override
    public void EliminarUsuario(Long id_usuario) {
        data.deleteById(id_usuario);
    }
}
