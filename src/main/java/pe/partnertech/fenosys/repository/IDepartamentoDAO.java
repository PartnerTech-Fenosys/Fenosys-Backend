/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.partnertech.fenosys.model.Departamento;

import java.util.Optional;
import java.util.Set;

@Repository
public interface IDepartamentoDAO extends JpaRepository<Departamento, Long> {

    @Query("SELECT d FROM Departamento d")
    Set<Departamento> findAllDepartamentos();

    @Query("SELECT d FROM Departamento d JOIN Pais p ON d.paisDepartamento.idPais = p.idPais " +
            "WHERE p.idPais = ?1")
    Set<Departamento> findDepartamentosByIDPais(Long id_pais);

    @Query("SELECT d FROM Departamento d JOIN Provincia p ON p.departamentoProvincia.idDepartamento = d.idDepartamento " +
            "WHERE p.idProvincia = ?1")
    Optional<Departamento> findDepartamentoByIDProvincia(Long id_provincia);
}
