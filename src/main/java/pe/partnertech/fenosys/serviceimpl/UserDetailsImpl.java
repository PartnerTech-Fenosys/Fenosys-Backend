/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.serviceimpl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pe.partnertech.fenosys.model.Usuario;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserDetailsImpl implements UserDetails {

    //Atributos
    private Long idUsuario;
    private String usernameUsuario;

    @JsonIgnore
    private String passwordUsuario;

    @JsonIgnore
    private String estadoUsuario;

    private Collection<? extends GrantedAuthority> authorities;

    //Constructores
    public UserDetailsImpl() {
    }

    public UserDetailsImpl(Long idUsuario, String usernameUsuario, String passwordUsuario, String estadoUsuario,
                           Collection<? extends GrantedAuthority> authorities) {
        this.idUsuario = idUsuario;
        this.usernameUsuario = usernameUsuario;
        this.passwordUsuario = passwordUsuario;
        this.estadoUsuario = estadoUsuario;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Usuario usuario) {
        List<GrantedAuthority> authorities = usuario.getRolesUsuario().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombreRol().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                usuario.getIdUsuario(),
                usuario.getUsernameUsuario(),
                usuario.getPasswordUsuario(),
                usuario.getEstadoUsuario(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    @Override
    public String getPassword() {
        return passwordUsuario;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    @Override
    public String getUsername() {
        return usernameUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl usuario = (UserDetailsImpl) o;
        return Objects.equals(idUsuario, usuario.idUsuario);
    }
}
