package com.example.oauth2.Modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "nut_categoria_comida")
public class CategoriaComida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria_comida")
    private Integer idCategoriaComida;

    @Column(name = "nombre_categoria")
    private String nombreCategoria;

    // Getters y setters

    public Integer getIdCategoriaComida() {
        return idCategoriaComida;
    }

    public void setIdCategoriaComida(Integer idCategoriaComida) {
        this.idCategoriaComida = idCategoriaComida;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}
