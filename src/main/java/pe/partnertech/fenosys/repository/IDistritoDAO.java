/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.partnertech.fenosys.model.Distrito;

import java.util.Set;

@Repository
public interface IDistritoDAO extends JpaRepository<Distrito, Long> {

    @Query("SELECT d FROM Distrito d")
    Set<Distrito> findAllDistritos();

    @Query("SELECT d FROM Distrito d JOIN Provincia p ON d.provinciaDistrito.idProvincia = p.idProvincia " +
            "WHERE p.idProvincia = ?1")
    Set<Distrito> findDistritosByIdProvincia(Long id_provincia);
}
