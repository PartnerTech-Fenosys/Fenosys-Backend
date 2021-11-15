/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.partnertech.fenosys.enums.RolNombre;
import pe.partnertech.fenosys.model.Rol;

import java.util.Optional;

@Repository
public interface IRolDAO extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByNombreRol(RolNombre rol);
}
