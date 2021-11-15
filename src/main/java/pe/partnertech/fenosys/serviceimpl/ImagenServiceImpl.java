/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.serviceimpl;

import org.springframework.stereotype.Service;
import pe.partnertech.fenosys.model.Imagen;
import pe.partnertech.fenosys.repository.IImagenDAO;
import pe.partnertech.fenosys.service.IImagenService;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ImagenServiceImpl implements IImagenService {

    final
    IImagenDAO data;

    public ImagenServiceImpl(IImagenDAO data) {
        this.data = data;
    }

    @Override
    public Optional<Imagen> BuscarImagen_ID(Long id_imagen) {
        return data.findById(id_imagen);
    }

    @Override
    public Optional<Imagen> BuscarImagen_Nombre(String nombre_imagen) {
        return data.findByNombreImagen(nombre_imagen);
    }

    @Override
    public void GuardarImagen(Imagen imagen) {
        data.save(imagen);
    }
}
