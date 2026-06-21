package com.usil.is2.grupo3.api.essalud;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "padron_essalud")
public class SeguroEsSalud {

    @Id
    @Column(name = "numero_dni", length = 8, nullable = false, unique = true)
    private String dni;

    @Column(length = 20, nullable = false)
    private String estado; // se asume que todos los registros en esta tabla son "Activo"

    @NotBlank(message = "El tipo de seguro es obligatorio")
    @Pattern(regexp = "Seguro Regular \\(\\+SEGURO\\)|Seguro Potestativo \\(\\+SALUD\\)|Seguro Agrario|SCTR \\(\\+PROTECCIÓN\\)|Seguro \\+VIDA",
             message = "El tipo de seguro debe ser uno de los permitidos: Seguro Regular (+SEGURO), Seguro Potestativo (+SALUD), Seguro Agrario, SCTR (+PROTECCIÓN) o Seguro +VIDA")
    @Column(name = "tipo_seguro", length = 50, nullable = false)
    private String tipoSeguro; // Ejemplo: "Seguro Regular (+SEGURO)", "Seguro Potestativo (+SALUD)", "Seguro Agrario", "SCTR (+PROTECCIÓN)" y "Seguro +VIDA".

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