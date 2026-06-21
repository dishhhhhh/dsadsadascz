package com.usil.is2.grupo3.triaje.controller;

import com.usil.is2.grupo3.admision.model.HistoriaClinica;
import com.usil.is2.grupo3.admision.repository.HistoriaClinicaRepository;
import com.usil.is2.grupo3.citas.model.CitaMedica;
import com.usil.is2.grupo3.citas.repository.CitaMedicaRepository;
import com.usil.is2.grupo3.triaje.model.RegistroTriaje;
import com.usil.is2.grupo3.triaje.service.TriajeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/triaje")
public class TriajeController {

    private final TriajeService triajeService;
    private final CitaMedicaRepository citaRepository;
    private final HistoriaClinicaRepository historiaRepository;

    public TriajeController(TriajeService triajeService, CitaMedicaRepository citaRepository, HistoriaClinicaRepository historiaRepository) {
        this.triajeService = triajeService;
        this.citaRepository = citaRepository;
        this.historiaRepository = historiaRepository;
    }

    // 1. Pantalla de la Cola de Atención (Lista las citas "Pendientes")
    @GetMapping("/cola")
    public String verColaDeAtencion(Model model) {
        List<CitaMedica> pendientes = citaRepository.findByEstadoCita("Pendiente");
        model.addAttribute("citasPendientes", pendientes);
        return "triaje/cola";
    }

    // 2. Pantalla del Formulario de Triaje (Requiere el ID de la cita a atender)
    @GetMapping("/registrar")
    public String mostrarFormularioTriaje(@RequestParam("idCita") Integer idCita, Model model) {
        CitaMedica cita = citaRepository.findById(idCita)
                .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada"));

        // se busca la historia clínica para que el enfermero vea los antecedentes (RF12)
        HistoriaClinica historia = historiaRepository.findByPaciente_IdPaciente(cita.getPaciente().getIdPaciente())
                .orElse(null);

        model.addAttribute("citaInfo", cita);
        model.addAttribute("historiaInfo", historia);
        model.addAttribute("registroTriaje", new RegistroTriaje());

        return "triaje/registrar";
    }

    // 3. Procesa el guardado del Triaje
    @PostMapping("/registrar")
    public String guardarTriaje(@RequestParam("idCita") Integer idCita,
                                @ModelAttribute("registroTriaje") RegistroTriaje registroTriaje,
                                Model model) {
        try {
            triajeService.registrarTriaje(idCita, registroTriaje);
            return "redirect:/triaje/cola?exito=true";
        } catch (Exception e) {
            return "redirect:/triaje/registrar?idCita=" + idCita + "&error=" + e.getMessage();
        }
    }
}