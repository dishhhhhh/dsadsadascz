package com.usil.is2.grupo3.admision.controller;

import com.usil.is2.grupo3.admision.model.Paciente;
import com.usil.is2.grupo3.admision.repository.SeguroMedicoRepository;
import com.usil.is2.grupo3.admision.service.AdmisionService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admision")
public class AdmisionController {

    private final AdmisionService admisionService;
    private final SeguroMedicoRepository seguroMedicoRepository;

    // Inyección de dependencias
    public AdmisionController(AdmisionService admisionService, SeguroMedicoRepository seguroMedicoRepository) {
        this.admisionService = admisionService;
        this.seguroMedicoRepository = seguroMedicoRepository;
    }

    // Muestra la pantalla del formulario
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("paciente", new Paciente());
        // Enviamos la lista de seguros para el menú desplegable
        model.addAttribute("seguros", seguroMedicoRepository.findAll());

        // Retorna el archivo HTML ubicado en: src/main/resources/templates/admision/formulario.html
        return "admision/formulario";
    }

    // Procesa el formulario cuando el usuario hace clic en "Guardar"
    @PostMapping("/registro")
    public String registrarPaciente(@Valid @ModelAttribute("paciente") Paciente paciente,
                                    BindingResult result,
                                    Model model) {
        // Si hay errores de validación (ej. DNI muy corto, campos vacíos)
        if (result.hasErrors()) {
            // Debemos volver a enviar la lista de seguros porque la página se recargará
            model.addAttribute("seguros", seguroMedicoRepository.findAll());
            return "admision/formulario";
        }

        // Si todo está correcto, se guarda el paciente en la base de datos
        try {
            Paciente pacienteGuardado = admisionService.registrarPaciente(paciente);
            return "redirect:/admision/registro?exito&idPaciente=" + pacienteGuardado.getIdPaciente();
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            model.addAttribute("seguros", seguroMedicoRepository.findAll());
            model.addAttribute("errorDni", "El DNI " + paciente.getNumeroDni() + " ya está registrado en el sistema.");
            return "admision/formulario";
        }
    }
}