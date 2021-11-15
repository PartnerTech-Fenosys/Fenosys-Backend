/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.controller.gestion_fenologica;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RegistroDiarioController {


    @PostMapping("/planilla/{id}/registro_diario/agregar")
    public ResponseEntity<?> AgregarRegistroDiarioFenologico() {
        return null;
    }
}
