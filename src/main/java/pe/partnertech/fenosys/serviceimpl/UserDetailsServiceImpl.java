/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.serviceimpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.partnertech.fenosys.model.Usuario;
import pe.partnertech.fenosys.repository.IUsuarioDAO;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    final
    IUsuarioDAO data;

    public UserDetailsServiceImpl(IUsuarioDAO data) {
        this.data = data;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = data.findByUsernameUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario No Encontrado: " + username));

        return UserDetailsImpl.build(usuario);
    }
}
