/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.controller.util.util_code;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pe.partnertech.fenosys.dto.response.general.ImagenResponse;
import pe.partnertech.fenosys.dto.response.general.MessageResponse;
import pe.partnertech.fenosys.dto.response.profile.BasicInfoUserResponse;
import pe.partnertech.fenosys.model.*;
import pe.partnertech.fenosys.service.*;

import java.util.Optional;

public class Code_ProfileBasicInfo {

    final
    IUsuarioService usuarioService;

    final
    IImagenService imagenService;

    final
    IPaisService paisService;

    final
    IDepartamentoService departamentoService;

    final
    IProvinciaService provinciaService;

    public Code_ProfileBasicInfo(IUsuarioService usuarioService, IImagenService imagenService,
                                 IPaisService paisService, IDepartamentoService departamentoService,
                                 IProvinciaService provinciaService) {
        this.usuarioService = usuarioService;
        this.imagenService = imagenService;
        this.paisService = paisService;
        this.departamentoService = departamentoService;
        this.provinciaService = provinciaService;
    }

    public ResponseEntity<?> ProfileUsuario(Long id_usuario) {
        Optional<Usuario> usuario_data = usuarioService.BuscarUsuario_By_IDUsuario(id_usuario);

        if (usuario_data.isPresent()) {
            Usuario usuario = usuario_data.get();

            Optional<Imagen> imagen_data = imagenService.BuscarImagen_ID(usuario.getImagenUsuario().getIdImagen());

            if (imagen_data.isPresent()) {
                Imagen foto = imagen_data.get();

                Distrito distrito = usuario.getDistritoUsuario();
                Provincia provincia = SendProvinciaByDistrito(distrito);
                Departamento departamento = SendDepartamentoByProvincia(provincia);
                Pais pais = SendPaisByDepartamento(departamento);

                return new ResponseEntity<>(new BasicInfoUserResponse(
                        usuario.getNombreUsuario(),
                        usuario.getApellidoUsuario(),
                        pais.getNombrePais(),
                        departamento.getNombreDepartamento(),
                        provincia.getNombreProvincia(),
                        distrito.getNombreDistrito(),
                        usuario.getEmailUsuario(),
                        new ImagenResponse(foto.getNombreImagen(), foto.getUrlImagen())
                ), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new MessageResponse("No se encuentra la foto de perfil del usuario."),
                        HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(new MessageResponse("No se encuentra información del perfil del usuario."),
                    HttpStatus.NOT_FOUND);
        }
    }

    Provincia SendProvinciaByDistrito(Distrito distrito) {
        Optional<Provincia> provincia_data = provinciaService.BuscarProvincia_IDDistrito(distrito.getIdDistrito());

        return provincia_data.orElse(null);
    }

    Departamento SendDepartamentoByProvincia(Provincia provincia) {
        Optional<Departamento> departamento_data = departamentoService.BuscarDepartamento_By_IDProvincia(provincia.getIdProvincia());

        return departamento_data.orElse(null);
    }

    Pais SendPaisByDepartamento(Departamento departamento) {
        Optional<Pais> pais_data = paisService.BuscarPais_By_IDDepartamento(departamento.getIdDepartamento());

        return pais_data.orElse(null);
    }
}
