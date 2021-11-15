/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.controller.util.util_code;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.partnertech.fenosys.dto.response.general.MessageResponse;
import pe.partnertech.fenosys.model.Imagen;
import pe.partnertech.fenosys.model.Usuario;
import pe.partnertech.fenosys.service.IImagenService;
import pe.partnertech.fenosys.service.IUsuarioService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

public class Code_UploadFoto {

    public static void AssignFoto(Usuario usuario, InputStream fotoStream, IImagenService imagenService) throws IOException {

        String nombre_foto = UUID.randomUUID() + usuario.getIdUsuario().toString() + UUID.randomUUID()
                + ".png";

        String url_foto = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/photos/")
                .path(nombre_foto)
                .toUriString();

        assert fotoStream != null;
        byte[] file_foto = IOUtils.toByteArray(fotoStream);

        Imagen imagen = new Imagen(
                nombre_foto,
                "image/png",
                url_foto,
                file_foto,
                usuario
        );

        imagenService.GuardarImagen(imagen);
    }

    public ResponseEntity<?> UpdateFoto(Long id_usuario, @RequestPart("foto") MultipartFile foto,
                                        IUsuarioService usuarioService, IImagenService imagenService) {

        Optional<Usuario> usuario_data = usuarioService.BuscarUsuario_By_IDUsuario(id_usuario);

        if (usuario_data.isPresent()) {
            Usuario usuario = usuario_data.get();

            Optional<Imagen> imagen_data = imagenService.BuscarImagen_ID(usuario.getImagenUsuario().getIdImagen());

            if (imagen_data.isPresent()) {
                try {
                    Imagen imagen = imagen_data.get();

                    if (!foto.isEmpty()) {
                        imagen.setArchivoImagen(foto.getBytes());
                        imagen.setTipoarchivoImagen(foto.getContentType());

                        imagenService.GuardarImagen(imagen);

                        return new ResponseEntity<>(new MessageResponse("Se ha actualizado su foto de perfil satisfactoriamente."),
                                HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(new MessageResponse("No se ha seleccionado un archivo"),
                                HttpStatus.BAD_REQUEST);
                    }
                } catch (Exception e) {
                    return new ResponseEntity<>(new MessageResponse("No se puede subir el archivo " + e),
                            HttpStatus.EXPECTATION_FAILED);
                }
            } else {
                return new ResponseEntity<>(new MessageResponse("No se encontró la información requerida."),
                        HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(new MessageResponse("No se encontró información del usuario."),
                    HttpStatus.NOT_FOUND);
        }
    }
}
