/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.partnertech.fenosys.model.Pais;

import java.util.Optional;
import java.util.Set;

@Repository
public interface IPaisDAO extends JpaRepository<Pais, Long> {

    @Query("SELECT p FROM Pais p")
    Set<Pais> findAllPaises();

    @Query("SELECT p FROM Pais p JOIN Departamento d ON d.paisDepartamento.idPais = p.idPais " +
            "WHERE d.idDepartamento = ?1")
    Optional<Pais> findPaisByIDDepartamento(Long id_departamento);
}
