package com.usil.is2.grupo3.triaje.repository;

import com.usil.is2.grupo3.triaje.model.RegistroTriaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RegistroTriajeRepository extends JpaRepository<RegistroTriaje, Integer> {

    // Busca si una cita ya tiene un triaje registrado
    Optional<RegistroTriaje> findByCitaMedica_IdCitaMedica(Integer idCitaMedica);
}