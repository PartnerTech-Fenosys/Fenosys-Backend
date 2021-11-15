/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.serviceimpl;

import org.springframework.stereotype.Service;
import pe.partnertech.fenosys.model.Distrito;
import pe.partnertech.fenosys.repository.IDistritoDAO;
import pe.partnertech.fenosys.service.IDistritoService;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class DistritoServiceImpl implements IDistritoService {

    final
    IDistritoDAO data;

    public DistritoServiceImpl(IDistritoDAO data) {
        this.data = data;
    }

    @Override
    public Set<Distrito> MostrarDistritos() {
        return data.findAllDistritos();
    }

    @Override
    public Set<Distrito> BuscarDistritos_By_IDProvincia(Long id_provincia) {
        return data.findDistritosByIdProvincia(id_provincia);
    }

    @Override
    public Optional<Distrito> BuscarDistrito_By_IDDistrito(Long id_distrito) {
        return data.findById(id_distrito);
    }
}
