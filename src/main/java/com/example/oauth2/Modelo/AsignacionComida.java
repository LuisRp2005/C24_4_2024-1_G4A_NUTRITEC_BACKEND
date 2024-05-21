package com.example.oauth2.Modelo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "nut_asignacion_comida")
public class AsignacionComida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignacion_comida")
    private Integer idAsignacionComida;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_comida", referencedColumnName = "id_comida")
    private Comida comida;

    @Column(name = "fecha_hora_registro")
    private LocalDateTime fechaHoraRegistro;

    public Integer getIdAsignacionComida() {
        return idAsignacionComida;
    }

    public void setIdAsignacionComida(Integer idAsignacionComida) {
        this.idAsignacionComida = idAsignacionComida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Comida getComida() {
        return comida;
    }

    public void setComida(Comida comida) {
        this.comida = comida;
    }

    public LocalDateTime getFechaHoraRegistro() {
        return fechaHoraRegistro;
    }

    public void setFechaHoraRegistro(LocalDateTime fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
    }
}
