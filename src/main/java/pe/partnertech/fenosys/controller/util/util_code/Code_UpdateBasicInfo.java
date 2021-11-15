/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.controller.util.util_code;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import pe.partnertech.fenosys.dto.request.usuario.UpdateBasicInfoRequest;
import pe.partnertech.fenosys.dto.response.general.MessageResponse;
import pe.partnertech.fenosys.model.Distrito;
import pe.partnertech.fenosys.model.Usuario;
import pe.partnertech.fenosys.service.IDistritoService;
import pe.partnertech.fenosys.service.IUsuarioService;

import java.util.Optional;

public class Code_UpdateBasicInfo {

    public ResponseEntity<?> UpdateBasicInfo(Long id_usuario,
                                             @RequestBody UpdateBasicInfoRequest updateBasicInfoRequest,
                                             IUsuarioService usuarioService, IDistritoService distritoService) {

        Optional<Usuario> usuario_data = usuarioService.BuscarUsuario_By_IDUsuario(id_usuario);

        if (usuario_data.isPresent()) {
            Optional<Distrito> distrito_data = distritoService.BuscarDistrito_By_IDDistrito(updateBasicInfoRequest.getDistritoUsuario());

            if (distrito_data.isPresent()) {
                Usuario usuario = usuario_data.get();

                Distrito distrito = distrito_data.get();

                usuario.setNombreUsuario(updateBasicInfoRequest.getNombreUsuario());
                usuario.setApellidoUsuario(updateBasicInfoRequest.getApellidoUsuario());
                usuario.setDistritoUsuario(distrito);

                usuarioService.GuardarUsuario(usuario);

                return new ResponseEntity<>(new MessageResponse("Se ha actualizado sus datos satisfactoriamente."),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new MessageResponse("Ocurrió un error al buscar su Ubicación."),
                        HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(new MessageResponse("No se encontró información del usuario."),
                    HttpStatus.NOT_FOUND);
        }
    }
}
