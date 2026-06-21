package com.usil.is2.grupo3.api.essalud;

import jakarta.persistence.*;

@Entity
@Table(name = "padron_essalud")
public class SeguroEsSalud {

    @Id
    @Column(name = "numero_dni", length = 8, nullable = false, unique = true)
    private String dni;

    @Column(length = 20, nullable = false)
    private String estado;

    @Column(name = "tipo_seguro", length = 50, nullable = false)
    private String tipoSeguro;

    // Constructor vacío exigido por JPA
    public SeguroEsSalud() {
    }

    public SeguroEsSalud(String dni, String estado, String tipoSeguro) {
        this.dni = dni;
        this.estado = estado;
        this.tipoSeguro = tipoSeguro;
    }

    // Getters y Setters
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getTipoSeguro() { return tipoSeguro; }
    public void setTipoSeguro(String tipoSeguro) { this.tipoSeguro = tipoSeguro; }
}