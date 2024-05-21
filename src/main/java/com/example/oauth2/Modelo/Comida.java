package com.example.oauth2.Modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "nut_comida")
public class Comida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comida")
    private Integer idComida;

    @ManyToOne
    @JoinColumn(name = "id_categoria_comida", referencedColumnName = "id_categoria_comida")
    private CategoriaComida categoriaComida;

    @Column(name = "nombre_comida")
    private String nombreComida;

    @Column(name = "calorias")
    private Integer calorias;

    public Integer getIdComida() {
        return idComida;
    }

    public void setIdComida(Integer idComida) {
        this.idComida = idComida;
    }

    public CategoriaComida getCategoriaComida() {
        return categoriaComida;
    }

    public void setCategoriaComida(CategoriaComida categoriaComida) {
        this.categoriaComida = categoriaComida;
    }

    public String getNombreComida() {
        return nombreComida;
    }

    public void setNombreComida(String nombreComida) {
        this.nombreComida = nombreComida;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }
}
