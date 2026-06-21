package com.usil.is2.grupo3.citas.repository;

import com.usil.is2.grupo3.citas.model.DisponibilidadMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface DisponibilidadMedicaRepository extends JpaRepository<DisponibilidadMedica, Integer> {

    // Busca disponibilidades de un médico en una fecha específica que estén "Disponibles"
    List<DisponibilidadMedica> findByMedico_IdMedicoAndFechaTurnoAndEstadoTurno(
            Integer idMedico, LocalDate fechaTurno, String estadoTurno);
}