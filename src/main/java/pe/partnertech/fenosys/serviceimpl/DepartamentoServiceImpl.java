/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.serviceimpl;

import org.springframework.stereotype.Service;
import pe.partnertech.fenosys.model.Departamento;
import pe.partnertech.fenosys.repository.IDepartamentoDAO;
import pe.partnertech.fenosys.service.IDepartamentoService;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class DepartamentoServiceImpl implements IDepartamentoService {

    final
    IDepartamentoDAO data;

    public DepartamentoServiceImpl(IDepartamentoDAO data) {
        this.data = data;
    }

    @Override
    public Set<Departamento> MostrarDepartamentos() {
        return data.findAllDepartamentos();
    }

    @Override
    public Set<Departamento> BuscarDepartamentos_By_IDPais(Long id_pais) {
        return data.findDepartamentosByIDPais(id_pais);
    }

    @Override
    public Optional<Departamento> BuscarDepartamento_By_IDProvincia(Long id_provincia) {
        return data.findDepartamentoByIDProvincia(id_provincia);
    }
}
