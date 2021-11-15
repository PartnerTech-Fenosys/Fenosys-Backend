/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.controller.util.util_code;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pe.partnertech.fenosys.dto.response.general.MessageResponse;
import pe.partnertech.fenosys.model.Usuario;

import java.util.Optional;

public class Code_SignupValidations {

    public static ResponseEntity<?> SignupValidationResponse(Optional<Usuario> usuario_data) {
        Usuario usuario = usuario_data.get();

        switch (usuario.getEstadoUsuario()) {
            case "PENDIENTE":
                return new ResponseEntity<>(new MessageResponse("Ya se ha utilizado este correo electrónico para un " +
                        "proceso de registro previo."),
                        HttpStatus.CONFLICT);
            case "ACTIVO":
            case "BLOQUEADO":
                return new ResponseEntity<>(new MessageResponse("Ya se encuentra en uso este correo electrónico por " +
                        "un usuario registrado en el sistema."),
                        HttpStatus.CONFLICT);
            default:
                return new ResponseEntity<>(new MessageResponse("Ocurrió un error al determinar el " +
                        "estado de un usuario existente."),
                        HttpStatus.CONFLICT);
        }
    }
}
