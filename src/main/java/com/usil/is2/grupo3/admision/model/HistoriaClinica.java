package com.usil.is2.grupo3.admision.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "historia_clinica")
public class HistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historia_clinica")
    private Integer idHistoriaClinica;

    // Relación estricta de 1 a 1 con Paciente
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente", unique = true, nullable = false) //
    @NotNull(message = "La historia clínica debe estar vinculada a un paciente")
    private Paciente paciente;

    @Column(length = 255)
    private String alergias;

    @Column(name = "tipo_sangre", length = 5)
    private String tipoSangre;

    @NotNull(message = "La fecha de creación es obligatoria")
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column(name = "antecedentes_familiares", length = 255)
    private String antecedentesFamiliares;

    public HistoriaClinica() {
    }

    // Getters y Setters
    public Integer getIdHistoriaClinica() { return idHistoriaClinica; }
    public void setIdHistoriaClinica(Integer idHistoriaClinica) { this.idHistoriaClinica = idHistoriaClinica; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public String getAlergias() { return alergias; }
    public void setAlergias(String alergias) { this.alergias = alergias; }

    public String getTipoSangre() { return tipoSangre; }
    public void setTipoSangre(String tipoSangre) { this.tipoSangre = tipoSangre; }

    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getAntecedentesFamiliares() { return antecedentesFamiliares; }
    public void setAntecedentesFamiliares(String antecedentesFamiliares) { this.antecedentesFamiliares = antecedentesFamiliares; }
}