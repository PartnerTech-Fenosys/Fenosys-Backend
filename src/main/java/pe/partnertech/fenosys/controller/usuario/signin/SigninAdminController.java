/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.controller.usuario.signin;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pe.partnertech.fenosys.controller.util.util_code.Code_SigninValidations;
import pe.partnertech.fenosys.dto.request.usuario.SigninRequest;
import pe.partnertech.fenosys.dto.response.general.MessageResponse;
import pe.partnertech.fenosys.model.Usuario;
import pe.partnertech.fenosys.security.jwt.JwtProvider;
import pe.partnertech.fenosys.service.IUsuarioService;
import pe.partnertech.fenosys.serviceimpl.UserDetailsImpl;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SigninAdminController {

    final
    AuthenticationManager authenticationManager;

    final
    JwtProvider jwtProvider;

    final
    IUsuarioService usuarioService;

    public SigninAdminController(AuthenticationManager authenticationManager, JwtProvider jwtProvider,
                                 IUsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/admin/signin")
    public ResponseEntity<?> SignInAdmin(@RequestBody SigninRequest signInRequest) {

        Optional<Usuario> admin_data = usuarioService.BuscarUsuario_By_UsernameOrEmail(signInRequest.getUsernameUsuario());

        if (admin_data.isPresent()) {
            Usuario admin = admin_data.get();

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(admin.getUsernameUsuario(), signInRequest.getPasswordUsuario())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtProvider.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                return Code_SigninValidations.SigninUsuario(jwt, userDetails);
            } else {
                return new ResponseEntity<>(new MessageResponse("No cumple con los permisos para acceder al sistema."),
                        HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new MessageResponse("Usuario no encontrado en el sistema."),
                    HttpStatus.NOT_FOUND);
        }
    }
}
