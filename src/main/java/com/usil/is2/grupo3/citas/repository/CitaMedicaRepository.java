package com.usil.is2.grupo3.citas.repository;

import com.usil.is2.grupo3.citas.model.CitaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CitaMedicaRepository extends JpaRepository<CitaMedica, Integer> {

    // Sirve para el RF11: Listar las citas de un paciente
    List<CitaMedica> findByPaciente_IdPaciente(Integer idPaciente);

    // Metodo para buscar citas según su estado ("Pendiente", "Listo para Atención", etc.)
    List<CitaMedica> findByEstadoCita(String estadoCita);
}