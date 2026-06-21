package com.usil.is2.grupo3.triaje.service;

// se importan las entidades y repositorios de los otros dos módulos
import com.usil.is2.grupo3.admision.model.HistoriaClinica;
import com.usil.is2.grupo3.admision.repository.HistoriaClinicaRepository;
import com.usil.is2.grupo3.citas.model.CitaMedica;
import com.usil.is2.grupo3.citas.repository.CitaMedicaRepository;

// se importan las clases propias del módulo de triaje
import com.usil.is2.grupo3.triaje.model.RegistroTriaje;
import com.usil.is2.grupo3.triaje.repository.RegistroTriajeRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TriajeService {

    private final RegistroTriajeRepository triajeRepository;
    private final CitaMedicaRepository citaRepository;
    private final HistoriaClinicaRepository historiaRepository;

    // Inyección de dependencias por constructor
    public TriajeService(RegistroTriajeRepository triajeRepository,
                         CitaMedicaRepository citaRepository,
                         HistoriaClinicaRepository historiaRepository) {
        this.triajeRepository = triajeRepository;
        this.citaRepository = citaRepository;
        this.historiaRepository = historiaRepository;
    }

    @Transactional
    public RegistroTriaje registrarTriaje(Integer idCita, RegistroTriaje nuevoTriaje) {

        // 1. Validar que la cita médica del día realmente exista
        CitaMedica cita = citaRepository.findById(idCita)
                .orElseThrow(() -> new IllegalArgumentException("La cita médica especificada no existe."));

        // Validar que la cita no tenga ya un triaje registrado (Regla de negocio 1 a 1)
        Optional<RegistroTriaje> triajeExistente = triajeRepository.findByCitaMedica_IdCitaMedica(idCita);
        if (triajeExistente.isPresent()) {
            throw new IllegalStateException("Error: Esta cita ya cuenta con un registro de triaje completado.");
        }

        // 2. Obtener la historia clínica base del paciente asociado a la cita
        Integer idPaciente = cita.getPaciente().getIdPaciente();
        HistoriaClinica historia = historiaRepository.findByPaciente_IdPaciente(idPaciente)
                .orElseThrow(() -> new IllegalStateException("El paciente no tiene una Historia Clínica base. Debe pasar por Admisión primero."));

        // 3. Vincular automáticamente los documentos (Cumplimiento estricto del RF14)
        nuevoTriaje.setCitaMedica(cita);
        nuevoTriaje.setHistoriaClinica(historia);
        nuevoTriaje.setFechaTriaje(LocalDateTime.now());

        // 4. Actualizar el estado de la cita médica (Cumplimiento estricto del RF15)
        cita.setEstadoCita("Listo para Atención");
        citaRepository.save(cita);

        // 5. Guardar y retornar el nuevo registro de signos vitales
        return triajeRepository.save(nuevoTriaje);
    }
}