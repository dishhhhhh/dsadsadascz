package com.usil.is2.grupo3.admision.repository;

import com.usil.is2.grupo3.admision.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    // Metodo automático para buscar por DNI
    Optional<Paciente> findByNumeroDni(String numeroDni);
}