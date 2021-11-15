/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.partnertech.fenosys.model.RegistroDiario;

@Repository
public interface IRegistroDiarioDAO extends JpaRepository<RegistroDiario, Long> {

}
