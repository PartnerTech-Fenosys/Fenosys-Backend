/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.controller.usuario.update.administrador;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.partnertech.fenosys.controller.util.util_code.Code_UploadFoto;
import pe.partnertech.fenosys.service.IImagenService;
import pe.partnertech.fenosys.service.IUsuarioService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UpdateAdministradorPhotoController {

    final
    IUsuarioService usuarioService;

    final
    IImagenService imagenService;

    Code_UploadFoto code_uploadFoto;

    public UpdateAdministradorPhotoController(IUsuarioService usuarioService, IImagenService imagenService) {
        this.usuarioService = usuarioService;
        this.imagenService = imagenService;
    }

    @PutMapping("/admin/{id_admin}/update/foto")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> UpdateFotoAdministrador(@PathVariable("id_admin") Long id_admin,
                                                     @RequestPart("foto") MultipartFile foto) {

        return code_uploadFoto.UpdateFoto(id_admin, foto, usuarioService, imagenService);
    }
}
