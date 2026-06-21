package com.usil.is2.grupo3.admision.controller;

import com.usil.is2.grupo3.admision.model.HistoriaClinica;
import com.usil.is2.grupo3.admision.model.Paciente;
import com.usil.is2.grupo3.admision.repository.PacienteRepository;
import com.usil.is2.grupo3.admision.service.AdmisionService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admision/historia")
public class HistoriaClinicaController {

    private final AdmisionService admisionService;
    private final PacienteRepository pacienteRepository;

    public HistoriaClinicaController(AdmisionService admisionService, PacienteRepository pacienteRepository) {
        this.admisionService = admisionService;
        this.pacienteRepository = pacienteRepository;
    }

    // Ruta para mostrar el formulario, requiere que le pasemos el ID del paciente por la URL (ej: ?idPaciente=1)
    @GetMapping("/registrar")
    public String mostrarFormularioHistoria(@RequestParam("idPaciente") Integer idPaciente, Model model) {
        Paciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new IllegalArgumentException("Paciente no encontrado"));

        model.addAttribute("pacienteInfo", paciente);
        model.addAttribute("historiaClinica", new HistoriaClinica());

        return "admision/historia";
    }

    // Procesa el formulario para guardar la historia (Corregido)
    @PostMapping("/registrar")
    public String registrarHistoria(@RequestParam("idPaciente") Integer idPaciente,
                                    @ModelAttribute("historiaClinica") HistoriaClinica historiaClinica,
                                    Model model) {
        try {
            // El servicio se encarga de asignar la fecha y el paciente automáticamente
            admisionService.registrarHistoriaClinica(idPaciente, historiaClinica);
            return "redirect:/admision/historia/registrar?idPaciente=" + idPaciente + "&exito=true";
        } catch (IllegalStateException e) {
            // Si el RF05 detecta duplicidad, capturamos el error y lo mandamos a la vista
            model.addAttribute("errorDuplicado", e.getMessage());
            Paciente paciente = pacienteRepository.findById(idPaciente).orElse(null);
            model.addAttribute("pacienteInfo", paciente);
            return "admision/historia";
        }
    }
}