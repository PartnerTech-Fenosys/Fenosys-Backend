/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.controller.usuario.update.administrador;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.partnertech.fenosys.controller.util.util_code.Code_UpdateBasicInfo;
import pe.partnertech.fenosys.dto.request.usuario.UpdateBasicInfoRequest;
import pe.partnertech.fenosys.service.IDistritoService;
import pe.partnertech.fenosys.service.IUsuarioService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UpdateAdministradorBasicInfoController {

    final
    IUsuarioService usuarioService;

    final
    IDistritoService distritoService;

    Code_UpdateBasicInfo code_updateBasicInfo;

    public UpdateAdministradorBasicInfoController(IUsuarioService usuarioService, IDistritoService distritoService) {
        this.usuarioService = usuarioService;
        this.distritoService = distritoService;
    }

    @PutMapping("/admin/{id_admin}/update/basicinfo")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> UpdateBasicInfoAdministrador(@PathVariable("id_admin") Long id_admin,
                                                          @RequestBody UpdateBasicInfoRequest updateBasicInfoRequest) {

        return code_updateBasicInfo.UpdateBasicInfo(id_admin, updateBasicInfoRequest, usuarioService, distritoService);
    }
}
