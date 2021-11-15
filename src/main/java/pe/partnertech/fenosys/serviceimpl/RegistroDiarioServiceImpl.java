/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.serviceimpl;

import org.springframework.stereotype.Service;
import pe.partnertech.fenosys.repository.IRegistroDiarioDAO;
import pe.partnertech.fenosys.service.IRegistroDiarioService;

import javax.transaction.Transactional;

@Service
@Transactional
public class RegistroDiarioServiceImpl implements IRegistroDiarioService {

    final
    IRegistroDiarioDAO data;

    public RegistroDiarioServiceImpl(IRegistroDiarioDAO data) {
        this.data = data;
    }
}
