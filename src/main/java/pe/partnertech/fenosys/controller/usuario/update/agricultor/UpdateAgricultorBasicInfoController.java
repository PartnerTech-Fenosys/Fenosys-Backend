/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.controller.usuario.update.agricultor;


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
public class UpdateAgricultorBasicInfoController {

    final
    IUsuarioService usuarioService;

    final
    IDistritoService distritoService;

    Code_UpdateBasicInfo code_updateBasicInfo;

    public UpdateAgricultorBasicInfoController(IUsuarioService usuarioService, IDistritoService distritoService) {
        this.usuarioService = usuarioService;
        this.distritoService = distritoService;
    }

    @PutMapping("/agricultor/{id_agricultor}/update/basicinfo")
    @PreAuthorize("hasRole('ROLE_AGRICULTOR')")
    public ResponseEntity<?> UpdateBasicInfoAgricultor(@PathVariable("id_agricultor") Long id_agricultor,
                                                       @RequestBody UpdateBasicInfoRequest updateBasicInfoRequest) {

        return code_updateBasicInfo.UpdateBasicInfo(id_agricultor, updateBasicInfoRequest, usuarioService, distritoService);
    }
}
