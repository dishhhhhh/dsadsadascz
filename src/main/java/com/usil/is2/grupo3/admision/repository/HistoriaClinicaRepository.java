package com.usil.is2.grupo3.admision.repository;

import com.usil.is2.grupo3.admision.model.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Integer> {

    // Metodo para encontrar la historia clínica de un paciente específico
    Optional<HistoriaClinica> findByPaciente_IdPaciente(Integer idPaciente);
}