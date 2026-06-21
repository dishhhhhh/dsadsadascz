package com.usil.is2.grupo3.admision.repository;

import com.usil.is2.grupo3.admision.model.SeguroMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeguroMedicoRepository extends JpaRepository<SeguroMedico, Integer> {
}