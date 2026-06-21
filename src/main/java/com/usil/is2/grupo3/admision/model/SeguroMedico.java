package com.usil.is2.grupo3.admision.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "seguro_medico")
public class SeguroMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seguro_medico")
    private Integer idSeguroMedico;

    @NotBlank(message = "El estado de cobertura es obligatorio")
    @Column(name = "estado_cobertura", length = 50, nullable = false)
    private String estadoCobertura;

    @NotBlank(message = "El tipo de seguro es obligatorio")
    @Column(name = "tipo_seguro", length = 50, nullable = false)
    private String tipoSeguro;

    // Constructor vacío exigido por JPA
    public SeguroMedico() {
    }

    // Getters y Setters
    public Integer getIdSeguroMedico() { return idSeguroMedico; }
    public void setIdSeguroMedico(Integer idSeguroMedico) { this.idSeguroMedico = idSeguroMedico; }

    public String getEstadoCobertura() { return estadoCobertura; }
    public void setEstadoCobertura(String estadoCobertura) { this.estadoCobertura = estadoCobertura; }

    public String getTipoSeguro() { return tipoSeguro; }
    public void setTipoSeguro(String tipoSeguro) { this.tipoSeguro = tipoSeguro; }
}