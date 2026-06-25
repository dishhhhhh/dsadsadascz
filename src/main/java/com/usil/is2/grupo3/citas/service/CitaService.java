package com.usil.is2.grupo3.citas.service;

import com.usil.is2.grupo3.admision.model.Paciente;
import com.usil.is2.grupo3.admision.repository.PacienteRepository;
import com.usil.is2.grupo3.citas.model.CitaMedica;
import com.usil.is2.grupo3.citas.model.DisponibilidadMedica;
import com.usil.is2.grupo3.citas.repository.CitaMedicaRepository;
import com.usil.is2.grupo3.citas.repository.DisponibilidadMedicaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CitaService {

    private final CitaMedicaRepository citaMedicaRepository;
    private final DisponibilidadMedicaRepository disponibilidadRepository;
    private final PacienteRepository pacienteRepository;

    public CitaService(CitaMedicaRepository citaMedicaRepository,
                       DisponibilidadMedicaRepository disponibilidadRepository,
                       PacienteRepository pacienteRepository) {
        this.citaMedicaRepository = citaMedicaRepository;
        this.disponibilidadRepository = disponibilidadRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Transactional
    public CitaMedica reservarCita(Integer idPaciente, Integer idDisponibilidad) {
        Paciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado en el padrón."));

        DisponibilidadMedica disponibilidad = disponibilidadRepository.findById(idDisponibilidad)
                .orElseThrow(() -> new IllegalArgumentException("El bloque de horario médico no existe."));

        if (!disponibilidad.getEstadoTurno().equalsIgnoreCase("Disponible")) {
            throw new IllegalStateException("Error: El horario seleccionado ya se encuentra Ocupado.");
        }

        // Cumplimiento del RF09: Cambiar estado a Ocupado de forma automática
        disponibilidad.setEstadoTurno("Ocupado");
        disponibilidadRepository.save(disponibilidad);

        // Cumplimiento del RF08: Generar y vincular la transacción de la Cita Médica
        CitaMedica nuevaCita = new CitaMedica();
        nuevaCita.setPaciente(paciente);
        nuevaCita.setDisponibilidadMedica(disponibilidad);
        nuevaCita.setFechaRegistro(LocalDateTime.now());
        nuevaCita.setEstadoCita("Pendiente");

        return citaMedicaRepository.save(nuevaCita);
    }

    @Transactional(readOnly = true)
    public List<CitaMedica> obtenerCitasActivasDelPaciente(Integer idPaciente) {
        List<CitaMedica> historialCitas = citaMedicaRepository.findByPaciente_IdPaciente(idPaciente);

        // se filtran eficientemente en memoria descartando las citas canceladas
        historialCitas.removeIf(cita -> cita.getEstadoCita().equalsIgnoreCase("Cancelada"));

        return historialCitas;
    }

    @Transactional(readOnly = true)
    public CitaMedica obtenerPorId(Integer idCitaMedica) {

        return citaMedicaRepository.findById(idCitaMedica)
                .orElseThrow(() ->
                        new IllegalArgumentException("Cita no encontrada"));
    }

    @Transactional
    public void cancelarCita(Integer idCitaMedica) {

        CitaMedica cita = citaMedicaRepository.findById(idCitaMedica)
                .orElseThrow(() ->
                        new IllegalArgumentException("Cita no encontrada"));

        cita.setEstadoCita("Cancelada");

        DisponibilidadMedica disponibilidad =
                cita.getDisponibilidadMedica();

        disponibilidad.setEstadoTurno("Disponible");

        disponibilidadRepository.save(disponibilidad);
        citaMedicaRepository.save(cita);
    }

}