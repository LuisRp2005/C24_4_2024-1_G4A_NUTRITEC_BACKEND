package com.example.oauth2.Modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "nut_ejercicio")
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ejercicio")
    private Integer id;

    @Column(name = "nombre_ejercicio")
    private String nombre;

    @Column(name = "descripcion_ejercicio")
    private String descripcion;

    @Column(name = "nivel_dificultad")
    private String nivel;

    @ManyToOne
    @JoinColumn(name = "id_tipo_imc", referencedColumnName = "id_tipo_imc")
    private TipoIMC tipoImc;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public TipoIMC getTipoImc() {
        return tipoImc;
    }

    public void setTipoImc(TipoIMC tipoImc) {
        this.tipoImc = tipoImc;
    }
}