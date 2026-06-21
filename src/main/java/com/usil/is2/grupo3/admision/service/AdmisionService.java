package com.usil.is2.grupo3.admision.service;

import com.usil.is2.grupo3.admision.model.HistoriaClinica;
import com.usil.is2.grupo3.admision.model.Paciente;
import com.usil.is2.grupo3.admision.repository.HistoriaClinicaRepository;
import com.usil.is2.grupo3.admision.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AdmisionService {

    private final PacienteRepository pacienteRepository;
    private final HistoriaClinicaRepository historiaClinicaRepository;

    // Inyección de dependencias por constructor
    public AdmisionService(PacienteRepository pacienteRepository, HistoriaClinicaRepository historiaClinicaRepository) {
        this.pacienteRepository = pacienteRepository;
        this.historiaClinicaRepository = historiaClinicaRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Paciente> buscarPacientePorDni(String dni) {
        return pacienteRepository.findByNumeroDni(dni);
    }

    @Transactional
    public Paciente registrarPaciente(Paciente paciente) {
        // Aquí a futuro se podría conectar la simulación de la API de RENIEC
        return pacienteRepository.save(paciente);
    }

    @Transactional
    public HistoriaClinica registrarHistoriaClinica(Integer idPaciente, HistoriaClinica nuevaHistoria) {
        // Cumplimiento del RF05: Validar Duplicidad de Registros
        Optional<HistoriaClinica> historiaExistente = historiaClinicaRepository.findByPaciente_IdPaciente(idPaciente);

        if (historiaExistente.isPresent()) {
            throw new IllegalStateException("El paciente ya cuenta con una Historia Clínica registrada en el sistema.");
        }

        Paciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new IllegalArgumentException("El paciente solicitado no existe."));

        nuevaHistoria.setPaciente(paciente);
        nuevaHistoria.setFechaCreacion(LocalDate.now());

        return historiaClinicaRepository.save(nuevaHistoria);
    }
}