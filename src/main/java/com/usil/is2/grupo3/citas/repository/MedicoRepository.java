package com.usil.is2.grupo3.citas.repository;

import com.usil.is2.grupo3.citas.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
}