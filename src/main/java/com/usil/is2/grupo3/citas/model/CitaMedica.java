package com.usil.is2.grupo3.citas.model;

// se importa la entidad Paciente desde el módulo de admisión
import com.usil.is2.grupo3.admision.model.Paciente;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "cita_medica")
public class CitaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita_medica")
    private Integer idCitaMedica;

    // Relación Muchos a Uno: Un paciente puede tener varias citas en el tiempo
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_paciente", nullable = false)
    @NotNull(message = "La cita debe estar vinculada a un paciente")
    private Paciente paciente;

    // Relación Muchos a Uno: Una disponibilidad representa un turno que ocupa la cita
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_disponibilidad_medica", nullable = false)
    @NotNull(message = "La cita debe tener un horario de disponibilidad asignado")
    private DisponibilidadMedica disponibilidadMedica; // [cite: 231]

    @NotNull(message = "La fecha de registro es obligatoria")
    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro; // Se usa LocalDateTime para mapear el TIMESTAMP [cite: 232]

    @NotBlank(message = "El estado de la cita es obligatorio")
    @Column(name = "estado_cita", length = 50, nullable = false)
    private String estadoCita; // [cite: 233]

    public CitaMedica() {
    }

    // Getters y Setters
    public Integer getIdCitaMedica() { return idCitaMedica; }
    public void setIdCitaMedica(Integer idCitaMedica) { this.idCitaMedica = idCitaMedica; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public DisponibilidadMedica getDisponibilidadMedica() { return disponibilidadMedica; }
    public void setDisponibilidadMedica(DisponibilidadMedica disponibilidadMedica) { this.disponibilidadMedica = disponibilidadMedica; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public String getEstadoCita() { return estadoCita; }
    public void setEstadoCita(String estadoCita) { this.estadoCita = estadoCita; }
}