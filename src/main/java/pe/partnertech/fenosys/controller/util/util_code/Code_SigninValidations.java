/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.controller.util.util_code;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pe.partnertech.fenosys.dto.response.general.MessageResponse;
import pe.partnertech.fenosys.security.dto.JwtResponse;
import pe.partnertech.fenosys.serviceimpl.UserDetailsImpl;

public class Code_SigninValidations {

    public static ResponseEntity<?> SigninUsuario(String jwt, UserDetailsImpl userDetails) {
        switch (userDetails.getEstadoUsuario()) {
            case "PENDIENTE":
                return new ResponseEntity<>(new MessageResponse("Tiene un proceso de registro pendiente por " +
                        "culminar."), HttpStatus.BAD_REQUEST);
            case "INACTIVO":
                return new ResponseEntity<>(new MessageResponse("No tiene acceso al sistema por tener su " +
                        "cuenta inactiva"), HttpStatus.BAD_REQUEST);
            case "BLOQUEADO":
                return new ResponseEntity<>(new MessageResponse("Se ha bloqueado el acceso al sistema por " +
                        "tener un proceso pendiente en su cuenta."), HttpStatus.BAD_REQUEST);
            case "ACTIVO":
                return new ResponseEntity<>(new JwtResponse(
                        jwt,
                        userDetails.getIdUsuario(),
                        userDetails.getUsername(),
                        userDetails.getAuthorities()
                ), HttpStatus.OK);
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
