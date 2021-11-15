/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.controller.profile;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.partnertech.fenosys.controller.util.util_code.Code_ProfileBasicInfo;
import pe.partnertech.fenosys.dto.response.general.ImagenResponse;
import pe.partnertech.fenosys.dto.response.general.MessageResponse;
import pe.partnertech.fenosys.dto.response.profile.BasicInfoUserResponse;
import pe.partnertech.fenosys.model.Usuario;
import pe.partnertech.fenosys.service.IUsuarioService;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BasicInfoController {

    final
    IUsuarioService usuarioService;

    Code_ProfileBasicInfo controllerProfileBasicInfo;

    public BasicInfoController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/agricultor/{id_agricultor}/basicinfo")
    @PreAuthorize("hasRole('ROLE_AGRICULTOR')")
    public ResponseEntity<?> AgricultorProfile(@PathVariable("id_agricultor") Long id_agricultor) {

        return controllerProfileBasicInfo.ProfileUsuario(id_agricultor);
    }

    @GetMapping("/admin/{id_admin}/basicinfo")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> AdminProfile(@PathVariable("id_admin") Long id_admin) {

        return controllerProfileBasicInfo.ProfileUsuario(id_admin);
    }

    @GetMapping("/master/{id_master}/basicinfo")
    @PreAuthorize("hasRole('ROLE_MASTER')")
    public ResponseEntity<?> MasterProfile(@PathVariable("id_master") Long id_master) {

        Optional<Usuario> master_data = usuarioService.BuscarUsuario_By_IDUsuario(id_master);

        if (master_data.isPresent()) {
            Usuario master = master_data.get();

            return new ResponseEntity<>(new BasicInfoUserResponse(
                    master.getNombreUsuario(),
                    master.getApellidoUsuario(),
                    null,
                    null,
                    null,
                    null,
                    master.getEmailUsuario(),
                    new ImagenResponse(
                            master.getImagenUsuario().getNombreImagen(),
                            master.getImagenUsuario().getUrlImagen())
            ), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("No se encuentra información del perfil del usuario."),
                    HttpStatus.NOT_FOUND);
        }
    }
}
