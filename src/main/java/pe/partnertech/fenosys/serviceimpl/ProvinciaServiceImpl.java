/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.serviceimpl;

import org.springframework.stereotype.Service;
import pe.partnertech.fenosys.model.Provincia;
import pe.partnertech.fenosys.repository.IProvinciaDAO;
import pe.partnertech.fenosys.service.IProvinciaService;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ProvinciaServiceImpl implements IProvinciaService {

    final
    IProvinciaDAO data;

    public ProvinciaServiceImpl(IProvinciaDAO data) {
        this.data = data;
    }

    @Override
    public Set<Provincia> MostrarProvincias() {
        return data.findAllProvincias();
    }

    @Override
    public Set<Provincia> BuscarProvincias_IDDepartamento(Long id_departamento) {
        return data.findProvinciasByIDDepartamento(id_departamento);
    }

    @Override
    public Optional<Provincia> BuscarProvincia_IDDistrito(Long id_distrito) {
        return data.findProvinciaByIDDistrito(id_distrito);
    }
}
