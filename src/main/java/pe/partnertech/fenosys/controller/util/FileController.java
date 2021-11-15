/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.controller.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.partnertech.fenosys.dto.response.general.MessageResponse;
import pe.partnertech.fenosys.model.Imagen;
import pe.partnertech.fenosys.service.IImagenService;

import java.util.Optional;

@Controller
@CrossOrigin
public class FileController {

    final
    IImagenService imagenService;

    public FileController(IImagenService imagenService) {
        this.imagenService = imagenService;
    }

    @GetMapping("/photos/{nombre_archivo}")
    public ResponseEntity<?> DescargarFoto(@PathVariable String nombre_archivo) {

        Optional<Imagen> imagen_data = imagenService.BuscarImagen_Nombre(nombre_archivo);

        if (imagen_data.isPresent()) {

            Imagen imagen = imagen_data.get();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; nombre_archivo=\"" +
                            imagen.getNombreImagen() + "\"")
                    .body(imagen.getArchivoImagen());
        } else {
            return new ResponseEntity<>(new MessageResponse("Error al cargar la imagen"), HttpStatus.BAD_REQUEST);
        }
    }
}
