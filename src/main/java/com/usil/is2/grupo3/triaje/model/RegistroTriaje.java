package com.usil.is2.grupo3.triaje.model;

// se importan las clases de los otros módulos
import com.usil.is2.grupo3.citas.model.CitaMedica;
import com.usil.is2.grupo3.admision.model.HistoriaClinica;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "registro_triaje")
public class RegistroTriaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registro_triaje")
    private Integer idRegistroTriaje;

    // Relación 1 a 1: Una cita médica tiene un único triaje asignado para ese día [cite: 190]
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cita_medica", unique = true, nullable = false)
    @NotNull(message = "El triaje debe estar vinculado a una cita del día")
    private CitaMedica citaMedica;

    // Relación Muchos a 1: Una historia clínica acumula muchos registros de triaje a lo largo del tiempo [cite: 191]
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_historia_clinica", nullable = false)
    @NotNull(message = "El triaje debe adjuntarse al historial del paciente")
    private HistoriaClinica historiaClinica;

    @NotBlank(message = "El motivo de consulta es obligatorio")
    @Column(name = "motivo_consulta", length = 255, nullable = false)
    private String motivoConsulta;

    @NotBlank(message = "La presión arterial es obligatoria")
    @Column(name = "presion_arterial", length = 20, nullable = false)
    private String presionArterial;

    @NotNull(message = "La temperatura es obligatoria")
    @Column(precision = 4, scale = 2, nullable = false)
    private BigDecimal temperatura; // DECIMAL(4,2)

    @NotNull(message = "El peso es obligatorio")
    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal peso; // DECIMAL(5,2)

    @NotNull(message = "La talla es obligatoria")
    @Column(precision = 3, scale = 2, nullable = false)
    private BigDecimal talla; // DECIMAL(3,2)

    @NotNull(message = "La saturación de oxígeno es obligatoria")
    @Column(name = "saturacion_oxigeno", nullable = false)
    private Integer saturacionOxigeno;

    @NotNull(message = "La fecha de triaje es obligatoria")
    @Column(name = "fecha_triaje", nullable = false)
    private LocalDateTime fechaTriaje; // TIMESTAMP

    public RegistroTriaje() {
    }

    // Getters y Setters
    public Integer getIdRegistroTriaje() { return idRegistroTriaje; }
    public void setIdRegistroTriaje(Integer idRegistroTriaje) { this.idRegistroTriaje = idRegistroTriaje; }

    public CitaMedica getCitaMedica() { return citaMedica; }
    public void setCitaMedica(CitaMedica citaMedica) { this.citaMedica = citaMedica; }

    public HistoriaClinica getHistoriaClinica() { return historiaClinica; }
    public void setHistoriaClinica(HistoriaClinica historiaClinica) { this.historiaClinica = historiaClinica; }

    public String getMotivoConsulta() { return motivoConsulta; }
    public void setMotivoConsulta(String motivoConsulta) { this.motivoConsulta = motivoConsulta; }

    public String getPresionArterial() { return presionArterial; }
    public void setPresionArterial(String presionArterial) { this.presionArterial = presionArterial; }

    public BigDecimal getTemperatura() { return temperatura; }
    public void setTemperatura(BigDecimal temperatura) { this.temperatura = temperatura; }

    public BigDecimal getPeso() { return peso; }
    public void setPeso(BigDecimal peso) { this.peso = peso; }

    public BigDecimal getTalla() { return talla; }
    public void setTalla(BigDecimal talla) { this.talla = talla; }

    public Integer getSaturacionOxigeno() { return saturacionOxigeno; }
    public void setSaturacionOxigeno(Integer saturacionOxigeno) { this.saturacionOxigeno = saturacionOxigeno; }

    public LocalDateTime getFechaTriaje() { return fechaTriaje; }
    public void setFechaTriaje(LocalDateTime fechaTriaje) { this.fechaTriaje = fechaTriaje; }
}