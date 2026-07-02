package com.usil.is2.grupo3.citas.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "medico")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private Integer idMedico;

    @NotBlank(message = "El número CMP es obligatorio")
    @Column(name = "numero_cmp", length = 10, unique = true, nullable = false)
    private String numeroCmp;

    @NotBlank(message = "Los nombres son obligatorios")
    @Column(length = 100, nullable = false)
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Column(length = 100, nullable = false)
    private String apellidos;

    @NotBlank(message = "La especialidad es obligatoria")
    @Column(length = 100, nullable = false)
    private String especialidad;

    public Medico() {}

    public Integer getIdMedico() { return idMedico; }
    public void setIdMedico(Integer idMedico) { this.idMedico = idMedico; }

    public String getNumeroCmp() { return numeroCmp; }
    public void setNumeroCmp(String numeroCmp) { this.numeroCmp = numeroCmp; }

    public String getNombres() { return nombres; }
    public String getNombreCompleto() { return this.nombres + " " + this.apellidos; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
}