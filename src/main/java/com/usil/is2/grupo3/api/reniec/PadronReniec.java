package com.usil.is2.grupo3.api.reniec;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "padron_reniec")
public class PadronReniec {

    @Id
    @Column(name = "numero_dni", length = 8, nullable = false, unique = true)
    private String dni;

    @Column(length = 100, nullable = false)
    private String nombres;

    @Column(name = "apellido_paterno", length = 100, nullable = false)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", length = 100, nullable = false)
    private String apellidoMaterno;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    // Constructor vacío exigido por JPA
    public PadronReniec() {
    }

    public PadronReniec(String dni, String nombres, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento) {
        this.dni = dni;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getters y Setters
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; }

    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
}