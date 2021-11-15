/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.partnertech.fenosys.model.Provincia;

import java.util.Optional;
import java.util.Set;

@Repository
public interface IProvinciaDAO extends JpaRepository<Provincia, Long> {

    @Query("SELECT p FROM Provincia p")
    Set<Provincia> findAllProvincias();

    @Query("SELECT p FROM Provincia p JOIN Departamento d ON p.departamentoProvincia.idDepartamento = d.idDepartamento " +
            "WHERE d.idDepartamento = ?1")
    Set<Provincia> findProvinciasByIDDepartamento(Long id_departamento);

    @Query("SELECT p FROM Provincia p JOIN Distrito d ON d.provinciaDistrito.idProvincia = p.idProvincia " +
            "WHERE d.idDistrito = ?1")
    Optional<Provincia> findProvinciaByIDDistrito(Long id_distrito);
}
