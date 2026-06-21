package com.usil.is2.grupo3.citas.controller;

import com.usil.is2.grupo3.admision.model.Paciente;
import com.usil.is2.grupo3.admision.repository.PacienteRepository;
import com.usil.is2.grupo3.citas.model.DisponibilidadMedica;
import com.usil.is2.grupo3.citas.repository.DisponibilidadMedicaRepository;
import com.usil.is2.grupo3.citas.service.CitaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/citas")
public class CitaController {

    private final CitaService citaService;
    private final PacienteRepository pacienteRepository;
    private final DisponibilidadMedicaRepository disponibilidadRepository;

    public CitaController(CitaService citaService,
                          PacienteRepository pacienteRepository,
                          DisponibilidadMedicaRepository disponibilidadRepository) {
        this.citaService = citaService;
        this.pacienteRepository = pacienteRepository;
        this.disponibilidadRepository = disponibilidadRepository;
    }

    // Muestra la pantalla. Si recibe un parámetro "dni", intenta buscar al paciente.
    @GetMapping("/reservar")
    public String mostrarFormularioReserva(@RequestParam(value = "dni", required = false) String dni, Model model) {

        // 1. Siempre se cargan los horarios disponibles del médico para el día de hoy
        List<DisponibilidadMedica> horariosLibres = disponibilidadRepository
                .findByMedico_IdMedicoAndFechaTurnoAndEstadoTurno(1, LocalDate.now(), "Disponible");
        model.addAttribute("horarios", horariosLibres);

        // 2. Si el recepcionista ingresó un DNI, se buscará
        if (dni != null && !dni.trim().isEmpty()) {
            Optional<Paciente> pacienteOpt = pacienteRepository.findByNumeroDni(dni);

            if (pacienteOpt.isPresent()) {
                model.addAttribute("pacienteEncontrado", pacienteOpt.get());
            } else {
                model.addAttribute("errorBusqueda", "No se encontró ningún paciente con el DNI: " + dni);
            }
            // se devuelve el DNI a la vista para que no se borre de la barra de búsqueda
            model.addAttribute("dniBuscado", dni);
        }

        return "citas/reserva";
    }

    // Procesa la reserva de la cita. Recibe el ID del paciente y el ID de la disponibilidad seleccionada.
    @PostMapping("/reservar")
    public String registrarCita(@RequestParam("idPaciente") Integer idPaciente,
                                @RequestParam("idDisponibilidad") Integer idDisponibilidad,
                                Model model) {
        try {
            citaService.reservarCita(idPaciente, idDisponibilidad);
            return "redirect:/citas/reservar?exito=true";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            // Si hay error al guardar, se recarga la data para que no explote la página
            model.addAttribute("pacienteEncontrado", pacienteRepository.findById(idPaciente).orElse(null));
            model.addAttribute("horarios", disponibilidadRepository
                    .findByMedico_IdMedicoAndFechaTurnoAndEstadoTurno(1, LocalDate.now(), "Disponible"));
            return "citas/reserva";
        }
    }
}