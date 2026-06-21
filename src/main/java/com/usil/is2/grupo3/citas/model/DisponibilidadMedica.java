package com.usil.is2.grupo3.citas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "disponibilidad_medica")
public class DisponibilidadMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disponibilidad_medica")
    private Integer idDisponibilidadMedica;

    // Varios bloques de disponibilidad pertenecen a un mismo médico
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_medico", nullable = false)
    @NotNull(message = "Debe asignar un médico a este horario")
    private Medico medico;

    @NotNull(message = "La fecha del turno es obligatoria")
    @Column(name = "fecha_turno", nullable = false)
    private LocalDate fechaTurno;

    @NotNull(message = "La hora de inicio es obligatoria")
    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

    @NotBlank(message = "El estado del turno es obligatorio")
    @Column(name = "estado_turno", length = 50, nullable = false)
    private String estadoTurno;

    public DisponibilidadMedica() {
    }

    // Getters y Setters
    public Integer getIdDisponibilidadMedica() { return idDisponibilidadMedica; }
    public void setIdDisponibilidadMedica(Integer idDisponibilidadMedica) { this.idDisponibilidadMedica = idDisponibilidadMedica; }

    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }

    public LocalDate getFechaTurno() { return fechaTurno; }
    public void setFechaTurno(LocalDate fechaTurno) { this.fechaTurno = fechaTurno; }

    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }

    public String getEstadoTurno() { return estadoTurno; }
    public void setEstadoTurno(String estadoTurno) { this.estadoTurno = estadoTurno; }
}