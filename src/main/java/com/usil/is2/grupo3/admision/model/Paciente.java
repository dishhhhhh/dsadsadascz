package com.usil.is2.grupo3.admision.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Integer idPaciente;

    // Relación de muchos a uno (Muchos pacientes pueden tener el mismo tipo de seguro)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_seguro_medico", nullable = false)
    @NotNull(message = "Debe seleccionar un seguro médico validado")
    private SeguroMedico seguroMedico;

    @NotBlank(message = "El número de DNI es obligatorio")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 dígitos")
    @Column(name = "numero_dni", length = 8, unique = true, nullable = false)
    private String numeroDni;

    @NotBlank(message = "Los nombres son obligatorios")
    @Column(length = 100, nullable = false)
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Column(length = 100, nullable = false)
    private String apellidos;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser válida (en el pasado)")
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(length = 15)
    private String telefono;

    @Email(message = "Debe ingresar un formato de correo electrónico válido")
    @Column(length = 150)
    private String correo;

    // Constructor vacío exigido por JPA
    public Paciente() {
    }

    // Getters y Setters
    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer idPaciente) { this.idPaciente = idPaciente; }

    public SeguroMedico getSeguroMedico() { return seguroMedico; }
    public void setSeguroMedico(SeguroMedico seguroMedico) { this.seguroMedico = seguroMedico; }

    public String getNumeroDni() { return numeroDni; }
    public void setNumeroDni(String numeroDni) { this.numeroDni = numeroDni; }

    public String getNombres() { return nombres; }
    public String getNombreCompleto() { return this.nombres + " " + this.apellidos; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}