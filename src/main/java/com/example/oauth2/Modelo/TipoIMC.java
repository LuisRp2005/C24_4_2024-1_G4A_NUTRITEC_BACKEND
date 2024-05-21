package com.example.oauth2.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "nut_tipo_imc")
public class TipoIMC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_imc")
    private Integer idTipoImc;

    @Column(name = "tipo_imc")
    private String tipoImc;

    @Column(name = "descripcion_imc")
    private String descripcionImc;

    public Integer getIdTipoImc() {
        return idTipoImc;
    }

    public void setIdTipoImc(Integer idTipoImc) {
        this.idTipoImc = idTipoImc;
    }

    public String getTipoImc() {
        return tipoImc;
    }

    public void setTipoImc(String tipoImc) {
        this.tipoImc = tipoImc;
    }

    public String getDescripcionImc() {
        return descripcionImc;
    }

    public void setDescripcionImc(String descripcionImc) {
        this.descripcionImc = descripcionImc;
    }
}
