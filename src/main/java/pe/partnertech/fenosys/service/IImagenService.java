/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.service;

import pe.partnertech.fenosys.model.Imagen;

import java.util.Optional;

public interface IImagenService {

    Optional<Imagen> BuscarImagen_ID(Long id_imagen);

    Optional<Imagen> BuscarImagen_Nombre(String nombre_imagen);

    void GuardarImagen(Imagen imagen);
}
