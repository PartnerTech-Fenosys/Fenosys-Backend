/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.serviceimpl;

import org.springframework.stereotype.Service;
import pe.partnertech.fenosys.model.Pais;
import pe.partnertech.fenosys.repository.IPaisDAO;
import pe.partnertech.fenosys.service.IPaisService;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class PaisServiceImpl implements IPaisService {

    final
    IPaisDAO data;

    public PaisServiceImpl(IPaisDAO data) {
        this.data = data;
    }

    @Override
    public Set<Pais> MostrarPaises() {
        return data.findAllPaises();
    }

    @Override
    public Optional<Pais> BuscarPais_By_IDDepartamento(Long id_departamento) {
        return data.findPaisByIDDepartamento(id_departamento);
    }
}
